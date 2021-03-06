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

package com.liferay.portlet.trash;

import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileRank;
import com.liferay.portlet.documentlibrary.service.BaseDLAppTestCase;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileRankLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLIndexer;
import com.liferay.portlet.trash.service.TrashEntryServiceUtil;

import java.util.List;

/**
 * @author Alexander Chow
 */
public abstract class BaseDLTrashHandlerTestCase extends BaseDLAppTestCase {

	protected AssetEntry fetchAssetEntry(String className, long classPK)
		throws Exception {

		return AssetEntryLocalServiceUtil.fetchEntry(className, classPK);
	}

	protected int getActiveFileRanksCount(long fileEntryId) throws Exception {
		List<DLFileRank> dlFileRanks = DLFileRankLocalServiceUtil.getFileRanks(
			parentFolder.getGroupId(), parentFolder.getUserId());

		int count = 0;

		for (DLFileRank dlFileRank : dlFileRanks) {
			if (dlFileRank.getFileEntryId() == fileEntryId) {
				count++;
			}
		}

		return count;
	}

	protected int getNotInTrashCount() throws Exception {
		return DLAppServiceUtil.getFoldersAndFileEntriesAndFileShortcutsCount(
			parentFolder.getRepositoryId(), parentFolder.getFolderId(),
			WorkflowConstants.STATUS_ANY, false);
	}

	protected int getTrashEntriesCount() throws Exception {
		Object[] result = TrashEntryServiceUtil.getEntries(
			parentFolder.getGroupId());

		return (Integer)result[1];
	}

	protected boolean isAssetEntryVisible(String className, long classPK)
		throws Exception {

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
			className, classPK);

		return assetEntry.isVisible();
	}

	protected int searchFileEntriesCount() throws Exception {
		Thread.sleep(1000 * TestPropsValues.JUNIT_DELAY_FACTOR);

		Indexer indexer = IndexerRegistryUtil.getIndexer(DLIndexer.class);

		SearchContext searchContext = ServiceTestUtil.getSearchContext();

		Hits results = indexer.search(searchContext);

		return results.getLength();
	}

}