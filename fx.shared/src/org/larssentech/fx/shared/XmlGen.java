package org.larssentech.fx.shared;

import java.io.File;

import org.larssentech.fx.shared.objects.FxXmlSpec;

public class XmlGen {

	public static String generateHeader(File f, String user, String sendTo) {

		String xml =

				FxXmlSpec.ROOT_O +

						FxXmlSpec.ELEMENTS_O[0] + f.getName() + FxXmlSpec.ELEMENTS_C[0] +

						FxXmlSpec.ELEMENTS_O[1] + f.length() + FxXmlSpec.ELEMENTS_C[1] +

						FxXmlSpec.ELEMENTS_O[2] + "binary" + FxXmlSpec.ELEMENTS_C[2] +

						FxXmlSpec.ELEMENTS_O[3] + f.length() / FxConstants.ARRAY_SIZE + FxXmlSpec.ELEMENTS_C[3] +

						FxXmlSpec.ELEMENTS_O[4] + user + FxXmlSpec.ELEMENTS_C[4] +

						FxXmlSpec.ELEMENTS_O[5] + sendTo + FxXmlSpec.ELEMENTS_C[5] +

						FxXmlSpec.ROOT_C;

		return xml;
	}

}
