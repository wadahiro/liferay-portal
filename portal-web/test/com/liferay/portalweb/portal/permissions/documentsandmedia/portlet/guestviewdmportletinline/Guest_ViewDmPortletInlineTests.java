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

package com.liferay.portalweb.portal.permissions.documentsandmedia.portlet.guestviewdmportletinline;

import com.liferay.portalweb.portal.BaseTestSuite;
import com.liferay.portalweb.portal.permissions.documentsandmedia.utils.AddPageDmTest;
import com.liferay.portalweb.portal.permissions.documentsandmedia.utils.AddPortletDmTest;
import com.liferay.portalweb.portal.permissions.documentsandmedia.utils.SignInTest;
import com.liferay.portalweb.portal.permissions.documentsandmedia.utils.SignOutTest;
import com.liferay.portalweb.utils.TearDownDLDocumentTest;
import com.liferay.portalweb.utils.TearDownPageTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Brian Wing Shun Chan
 */
public class Guest_ViewDmPortletInlineTests extends BaseTestSuite {
	public static Test suite() {
		TestSuite testSuite = new TestSuite();
		testSuite.addTestSuite(AddPageDmTest.class);
		testSuite.addTestSuite(AddPortletDmTest.class);
		testSuite.addTestSuite(PermissionsGuestViewPortletOffTest.class);
		testSuite.addTestSuite(SignOutTest.class);
		testSuite.addTestSuite(Guest_NoViewDmPortletTest.class);
		testSuite.addTestSuite(SignInTest.class);
		testSuite.addTestSuite(PermissionsGuestViewPortletOnTest.class);
		testSuite.addTestSuite(SignOutTest.class);
		testSuite.addTestSuite(Guest_ViewDmPortletTest.class);
		testSuite.addTestSuite(SignInTest.class);
		testSuite.addTestSuite(TearDownDLDocumentTest.class);
		testSuite.addTestSuite(TearDownPageTest.class);

		return testSuite;
	}
}