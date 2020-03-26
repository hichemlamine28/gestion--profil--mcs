package com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class TechnicalException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private BaseDTO baseDTO;
	private ErrorsEnum errorsEnum;

	public TechnicalException(BaseDTO baseDTO, ErrorsEnum errorsEnum, Exception e) {
		super(e);
		this.baseDTO = baseDTO;
		this.errorsEnum = errorsEnum;
	}

	public TechnicalException(BaseDTO baseDTO, ErrorsEnum errorsEnum) {
		this.baseDTO = baseDTO;
		this.errorsEnum = errorsEnum;
	}

	public BaseDTO getBaseDTO() {
		return baseDTO;
	}

	public void setBaseDTO(BaseDTO baseDTO) {
		this.baseDTO = baseDTO;
	}

	public ErrorsEnum getErrorsEnum() {
		return errorsEnum;
	}

	public void setErrorsEnum(ErrorsEnum errorsEnum) {
		this.errorsEnum = errorsEnum;
	}

}
