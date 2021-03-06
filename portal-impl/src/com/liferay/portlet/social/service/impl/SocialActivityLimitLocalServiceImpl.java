/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.social.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.model.User;
import com.liferay.portlet.social.model.SocialActivityLimit;
import com.liferay.portlet.social.service.base.SocialActivityLimitLocalServiceBaseImpl;

/**
 * @author Zsolt Berentey
 */
public class SocialActivityLimitLocalServiceImpl
	extends SocialActivityLimitLocalServiceBaseImpl {

	@Transactional(
		propagation = Propagation.REQUIRES_NEW,
		rollbackFor = {PortalException.class, SystemException.class})
	public SocialActivityLimit addActivityLimit(
			long userId, long groupId, long classNameId, long classPK,
			int activityType, String activityCounterName, int limitPeriod)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		long activityLimitId = counterLocalService.increment();

		SocialActivityLimit activityLimit =
			socialActivityLimitPersistence.create(activityLimitId);

		activityLimit.setGroupId(groupId);
		activityLimit.setCompanyId(user.getCompanyId());
		activityLimit.setUserId(userId);
		activityLimit.setClassNameId(classNameId);
		activityLimit.setClassPK(classPK);
		activityLimit.setActivityType(activityType);
		activityLimit.setActivityCounterName(activityCounterName);
		activityLimit.setCount(limitPeriod, 0);

		socialActivityLimitPersistence.update(activityLimit, false);

		return activityLimit;
	}

	public SocialActivityLimit fetchActivityLimit(
			long groupId, long userId, long classNameId, long classPK,
			int activityType, String activityCounterName)
		throws SystemException {

		return socialActivityLimitPersistence.fetchByG_U_C_C_A_A(
			groupId, userId, classNameId, classPK, activityType,
			activityCounterName);
	}

}