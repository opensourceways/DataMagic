/*
 This project is licensed under the Mulan PSL v2.
 You can use this software according to the terms and conditions of the Mulan PSL v2.
 You may obtain a copy of Mulan PSL v2 at:
     http://license.coscl.org.cn/MulanPSL2
 THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 See the Mulan PSL v2 for more details.
 Created: 2025
*/

package com.om.DataMagic.infrastructure.pgDB.converter.dws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.om.DataMagic.common.constant.ContribConstant;
import com.om.DataMagic.common.constant.TableConstant;
import com.om.DataMagic.common.util.DateUtil;
import com.om.DataMagic.infrastructure.pgDB.dataobject.CommentDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.IssueDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.PRDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.dws.ContributeDO;
import com.om.DataMagic.process.codePlatform.gitcode.dws.contribute.BaseContribute;

public final class ContributeConverter {
    // Private constructor to prevent instantiation of the utility class
    private ContributeConverter() {
        throw new AssertionError("Cannot instantiate ContributeConverter class");
    }

    /**
     * Logger instance for ContributeConverter.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ContributeConverter.class);

    /**
     * Convert a BaseContribute object to a ContributeDO entity.
     *
     * @param obj The BaseContribute object to convert
     * @return A ContributeDO entity
     */
    public static ContributeDO toEntity(final BaseContribute obj) {
        ContributeDO contrib = new ContributeDO();
        contrib.setUserId(obj.getUserId());
        contrib.setUserLogin(obj.getUserLogin());
        contrib.setCompany(TableConstant.INDEPENDENT);
        contrib.setCompanyType(TableConstant.PERSONAL);
        contrib.setHtmlUrl(obj.getHtmlUrl());
        contrib.setNamespace(obj.getNamespace());
        contrib.setRepoPath(obj.getRepoPath());
        contrib.setSigName(TableConstant.SIG);
        contrib.setCodePlatform(obj.getCodePlatform());
        contrib.setTitle(obj.getTitle());
        contrib.setCreatedAt(obj.getCreatedAt());
        contrib.setUpdatedAt(obj.getUpdatedAt());
        contrib.setClosedAt(obj.getClosedAt());
        contrib.setState(obj.getState());
        contrib.setClosedTime(DateUtil.getDuration(obj.getCreatedAt(), obj.getClosedAt()));
        contrib.setIsRemoved(obj.getIsRemoved());
        contrib.setVersion(null);
        return contrib;
    }

    /**
     * Convert an object to a BaseContribute entity.
     *
     * @param obj The object to convert
     * @return A BaseContribute entity
     */
    public static BaseContribute toBaseContribute(final Object obj) {
        BaseContribute contrib = new BaseContribute();
        if (obj instanceof PRDO || obj instanceof IssueDO || obj instanceof CommentDO) {
            BeanUtils.copyProperties(obj, contrib);
        }
        return contrib;
    }

    /**
     * Convert a PRDO object to an ContributeDO entity.
     *
     * @param obj         The PRDO object to convert
     * @param baseContirb The BaseContribute object
     * @return A ContributeDO entity
     */
    public static ContributeDO toEntity(final PRDO obj, BaseContribute baseContirb) {
        ContributeDO contrib = toEntity(baseContirb);
        contrib.setUuid(obj.getUuid());
        contrib.setMergedAt(obj.getMergedAt());
        contrib.setAdd(obj.getAddedLines());
        contrib.setRemove(obj.getRemovedLines());
        contrib.setBranch(obj.getBaseLabel());
        contrib.setIsPr(1);
        contrib.setContribType(ContribConstant.PR);
        return contrib;
    }

    /**
     * Convert a CommentDO object to an ContributeDO entity.
     *
     * @param obj         The CommentDO object to convert
     * @param baseContirb The BaseContribute object
     * @return A ContributeDO entity
     */
    public static ContributeDO toEntity(final CommentDO obj, BaseContribute baseContirb) {
        ContributeDO contrib = toEntity(baseContirb);
        contrib.setUuid(obj.getUuid());
        contrib.setBody(obj.getBody());
        contrib.setIsComment(1);
        contrib.setCommentType(obj.getCommentType());
        contrib.setContribType(ContribConstant.COMMENT);
        return contrib;
    }

    /**
     * Convert an IssueDO object to an ContributeDO entity.
     *
     * @param obj         The IssueDO object to convert
     * @param baseContirb The BaseContribute object
     * @return A ContributeDO entity
     */
    public static ContributeDO toEntity(final IssueDO obj, BaseContribute baseContirb) {
        ContributeDO contrib = toEntity(baseContirb);
        contrib.setUuid(obj.getUuid());
        contrib.setIssueType(obj.getIssueType());
        contrib.setIsIssue(1);
        contrib.setContribType(ContribConstant.ISSUE);
        return contrib;
    }
}
