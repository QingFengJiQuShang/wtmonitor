package com.jrsoft.fri.dagl.action;

import java.util.Date;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class text {
	//��������
	public static void main(String[] args) {
		
		 // ���� ���Sapi.SpVoice����Ҫ��װʲô�����𣬸о�ƽ���޹ʾ�����
	    ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
	    // Dispatch����ʲô�ģ�
	    Dispatch sapo = sap.getObject();
	    try {
	 
	        // ���� 0-100
	        sap.setProperty("Volume", new Variant(100));
	        // �����ʶ��ٶ� -10 �� +10
	        sap.setProperty("Rate", new Variant(-2));
	 
	        Variant defalutVoice = sap.getProperty("Voice");
	 
	        Dispatch dispdefaultVoice = defalutVoice.toDispatch();
	        Variant allVoices = Dispatch.call(sapo, "GetVoices");
	        Dispatch dispVoices = allVoices.toDispatch();
	 
	        Dispatch setvoice = Dispatch.call(dispVoices, "Item", new Variant(1)).toDispatch();
	        ActiveXComponent voiceActivex = new ActiveXComponent(dispdefaultVoice);
	        ActiveXComponent setvoiceActivex = new ActiveXComponent(setvoice);
	 
	        Variant item = Dispatch.call(setvoiceActivex, "GetDescription");
	        // ִ���ʶ�
	        String name="������ �����Ϻ�";
	        Dispatch.call(sapo, "Speak", new Variant(name));
	 
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        sapo.safeRelease();
	        sap.safeRelease();
	    }

	}

}
