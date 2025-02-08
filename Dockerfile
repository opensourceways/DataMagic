FROM openeuler/openeuler:22.03-lts-sp1 as BUILDER

RUN sed -i "s|repo.openeuler.org|mirrors.nju.edu.cn/openeuler|g" /etc/yum.repos.d/openEuler.repo \
    && sed -i '/metalink/d' /etc/yum.repos.d/openEuler.repo \
    && sed -i '/metadata_expire/d' /etc/yum.repos.d/openEuler.repo

RUN cd / \
    && yum install -y wget \
    && wget https://mirrors.tuna.tsinghua.edu.cn/Adoptium/17/jdk/x64/linux/OpenJDK17U-jdk_x64_linux_hotspot_17.0.14_7.tar.gz \
    && tar -zxvf OpenJDK17U-jdk_x64_linux_hotspot_17.0.14_7.tar.gz \
    && wget https://repo.huaweicloud.com/apache/maven/maven-3/3.8.1/binaries/apache-maven-3.8.1-bin.tar.gz \
    && tar -zxvf apache-maven-3.8.1-bin.tar.gz

COPY . /DataMagic

ENV JAVA_HOME=/jdk-17.0.14+7
ENV PATH=${JAVA_HOME}/bin:$PATH

ENV MAVEN_HOME=/apache-maven-3.8.1
ENV PATH=${MAVEN_HOME}/bin:$PATH

RUN cd /DataMagic \
    && mvn clean install package -Dmaven.test.skip

FROM openeuler/openeuler:22.03-lts-sp1

RUN sed -i "s|repo.openeuler.org|mirrors.nju.edu.cn/openeuler|g" /etc/yum.repos.d/openEuler.repo \
    && sed -i '/metalink/d' /etc/yum.repos.d/openEuler.repo \
    && sed -i '/metadata_expire/d' /etc/yum.repos.d/openEuler.repo

RUN yum update -y \
    && yum install -y shadow passwd

RUN groupadd -g 1001 datamagic \
    && useradd -u 1001 -g datamagic -s /bin/bash -m datamagic

ENV WORKSPACE=/home/datamagic

WORKDIR ${WORKSPACE}

COPY --chown=datamagic --from=Builder /DataMagic/target/DataMagic-0.0.1-SNAPSHOT.jar ${WORKSPACE}/target/DataMagic-0.0.1-SNAPSHOT.jar

RUN echo "umask 027" >> /home/datamagic/.bashrc \
    && echo "umask 027" >> /root/.bashrc \
    && source /home/datamagic/.bashrc \
    && echo "set +o history" >> /etc/bashrc \
    && echo "set +o history" >> /home/datamagic/.bashrc \
    && sed -i "s|HISTSIZE=1000|HISTSIZE=0|" /etc/profile \
    && sed -i "s|PASS_MAX_DAYS[ \t]*99999|PASS_MAX_DAYS 30|" /etc/login.defs \
    && sed -i '4,6d' /home/datamagic/.bashrc

RUN passwd -l datamagic \
    && usermod -s /sbin/nologin sync \
    && usermod -s /sbin/nologin shutdown \
    && usermod -s /sbin/nologin halt \
    && usermod -s /sbin/nologin datamagic \
    && echo "export TMOUT=1800 readonly TMOUT" >> /etc/profile

RUN dnf install -y wget \
    && wget https://mirrors.tuna.tsinghua.edu.cn/Adoptium/17/jre/x64/linux/OpenJDK17U-jre_x64_linux_hotspot_17.0.14_7.tar.gz \
    && tar -zxvf OpenJDK17U-jre_x64_linux_hotspot_17.0.14_7.tar.gz \
    && rm OpenJDK17U-jre_x64_linux_hotspot_17.0.14_7.tar.gz \
    && chown -R datamagic:datamagic jdk-17.0.14+7-jre

RUN rm -rf `find / -iname "*tcpdump*"` \
    && rm -rf `find / -iname "*sniffer*"` \
    && rm -rf `find / -iname "*wireshark*"` \
    && rm -rf `find / -iname "*Netcat*"` \
    && rm -rf `find / -iname "*gdb*"` \
    && rm -rf `find / -iname "*strace*"` \
    && rm -rf `find / -iname "*readelf*"` \
    && rm -rf `find / -iname "*cpp*"` \
    && rm -rf `find / -iname "*gcc*"` \
    && rm -rf `find / -iname "*dexdump*"` \
    && rm -rf `find / -iname "*mirror*"` \
    && rm -rf `find / -iname "*JDK*"` \
    && rm -rf /root/.m2/repository/* \
    && rm -rf /tmp/* \
    && rm -rf ${WORKSPACE}/jdk-17.0.14+7-jre/bin/jfr \
    && rm -rf ${WORKSPACE}/jdk-17.0.14+7-jre/bin/jrunscript \
    && rm -rf ${WORKSPACE}/jdk-17.0.14+7-jre/bin/keytool \
    && rm -rf ${WORKSPACE}/jdk-17.0.14+7-jre/bin/rmiregistry

RUN rm -rf /usr/bin/gdb* \
    && rm -rf /usr/share/gdb \
    && rm -rf /usr/share/gcc-10.3.1 \
	&& yum remove gdb-gdbserver findutils passwd shadow -y \
    && yum clean all \
    && chmod 700 -R /home/datamagic

ENV JAVA_HOME=${WORKSPACE}/jdk-17.0.14+7-jre
ENV PATH=${JAVA_HOME}/bin:$PATH
ENV LANG="C.UTF-8"

USER datamagic

CMD java -Xmx6144m -Xms1024m -jar ${WORKSPACE}/target/DataMagic-0.0.1-SNAPSHOT.jar --spring.config.location=${APPLICATION_PATH}
