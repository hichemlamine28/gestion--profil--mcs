package com.arkeup.link_innov.gestion_profil_mcs.donnee.constants;

public enum ProfilAction {
	CONNECT("Connection"), UPDATE("Update"),
//
	IDCONNECT("1"), IDUPDATE("2");

	private String value;

	ProfilAction(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static ProfilAction fromString(String text) {
		if (text != null) {
			for (ProfilAction profilAction : ProfilAction.values()) {
				if (text.equalsIgnoreCase(profilAction.value)) {
					return profilAction;
				}
			}
		}
		return null;
	}
}
