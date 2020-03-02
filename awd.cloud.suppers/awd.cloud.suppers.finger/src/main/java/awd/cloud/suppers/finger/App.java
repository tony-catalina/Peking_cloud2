package awd.cloud.suppers.finger;

import java.io.IOException;

import javax.swing.JFrame;

import com.awd.finger.hongda.FuncAOutput;
import com.awd.finger.hongda.ID_Fpr;

import awd.cloud.suppers.finger.config.InitHongdaZwy;
import awd.cloud.suppers.finger.tools.FingerUtil;
/**
 * Hello world!
 *
 */
public class App extends JFrame 
{
    public static void main( String[] args ){
    	run();
    }

    public static void run() {
    	FuncAOutput output = new FuncAOutput();
    	//InitZwy.initBegin();
    	InitHongdaZwy.init();
    	System.err.println("app---");
    	String fingerData = FingerUtil.getTestTzm(1);
    	byte[] fingerData_byte;
    	try {
    		fingerData_byte = FingerUtil.getBmpByte(fingerData);
    		System.err.println("idfpr2----------- "+ID_Fpr.FP_FeatureMatch(fingerData_byte, fingerData_byte, output));
    		System.err.println("idfpr2----------- "+output.Get());
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}
    	
    	InitHongdaZwy.initClose();
	}
    
}
