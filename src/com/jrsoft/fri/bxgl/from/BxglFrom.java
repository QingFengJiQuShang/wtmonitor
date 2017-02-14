package com.jrsoft.fri.bxgl.from;

import com.jrsoft.fri.bxgl.entity.BxglClaim;
import com.jrsoft.fri.bxgl.entity.BxglSafe;
import com.jrsoft.fri.common.core.form.BaseForm;

public class BxglFrom extends BaseForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BxglSafe safe=new BxglSafe();
	private BxglClaim claim=new BxglClaim();

	public BxglSafe getSafe() {
		return safe;
	}

	public void setSafe(BxglSafe safe) {
		this.safe = safe;
	}

	public BxglClaim getClaim() {
		return claim;
	}

	public void setClaim(BxglClaim claim) {
		this.claim = claim;
	}
	
	
	
	

}
