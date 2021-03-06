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

package com.liferay.portlet;

import com.liferay.util.SerializableUtil;

import java.io.Serializable;

import javax.portlet.Event;

import javax.xml.namespace.QName;

/**
 * @author Brian Wing Shun Chan
 */
public class EventImpl implements Event {

	public EventImpl(String name, QName qName, Serializable value) {
		_name = name;
		_qName = qName;
		_value = value;
	}

	public String getName() {
		return _name;
	}

	public QName getQName() {
		return _qName;
	}

	public byte[] getSerializedValue() {
		if (_serializedValue == null) {
			_serializedValue = SerializableUtil.serialize(_value);
		}

		return _serializedValue;
	}

	public Serializable getValue() {
		return _value;
	}

	private String _name;
	private QName _qName;
	private byte[] _serializedValue;
	private Serializable _value;

}