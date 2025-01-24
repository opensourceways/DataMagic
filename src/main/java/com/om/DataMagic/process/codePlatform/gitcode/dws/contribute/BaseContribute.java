package com.om.DataMagic.process.codePlatform.gitcode.dws.contribute;

import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseContribute {
    /**
     * uuid of data.
     */
    private String uuid;

    /**
     * Login of user.
     */
    private String userLogin;

    /**
     * id of user.
     */
    private String userId;

    /**
     * The internal or external attribute of user.
     */
    private String internal;

    /**

    /**
     * The sig role of a user.
     */
    private String role;

    /**
     * The type of a company.
     */
    private String companyType;

    /**
     * The name of a company.
     */
    private String company;

    /**
     * The html url of a contribute.
     */
    private String htmlUrl;

    /**
     * The repo path of a contribute.
     */
    private String repoPath;

    /**
     * The repo sig name of a contribute.
     */
    private String sigName;

    /**
     * The repo namespace of a contribute.
     */
    private String namespace;

    /**
     * The platform of a user.
     */
    private String codePlatform;

    /**
     * The title of a pr or issue.
     */
    private String title;

    /**
     * The created time of a contribute.
     */
    private OffsetDateTime createdAt;

    /**
     * The updated time of a contribute.
     */
    private OffsetDateTime updatedAt;

    /**
     * The closed time of a issue.
     */
    private OffsetDateTime closedAt;

    /**
     * The merged time of a pr.
     */
    private OffsetDateTime mergedAt;

    /**
     * The state of a pr or issue.
     */
    private String state;

    /**
     * The contribute type of a contribute.
     */
    private String contribType;

    /**
     * The first reply time of a pr or issue.
     */
    private Long firstReplyTime;

    /**
     * The comment number of a pr or issue.
     */
    private Integer commentNum;

    /**
     * The closed duration time of a pr or issue.
     */
    private Long closedTime;
}
