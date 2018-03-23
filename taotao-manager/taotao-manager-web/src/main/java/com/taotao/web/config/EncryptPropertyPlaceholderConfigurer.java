package com.taotao.web.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.taotao.common.util.DESUtil;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	private String[] encryptPropNames = { "jdbc.username", "jdbc.password" };

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {

		// 如果在加密属性名单中发现该属性
		if (isEncryptProp(propertyName)) {
			String decryptValue = DESUtil.getDecryptString(propertyValue);
			//System.out.println(decryptValue);
			return decryptValue;
		} else {
			return propertyValue;
		}

	}

	private boolean isEncryptProp(String propertyName) {
		for (String encryptName : encryptPropNames) {
			if (encryptName.equals(propertyName)) {
				return true;
			}
		}
		return false;
	}
}