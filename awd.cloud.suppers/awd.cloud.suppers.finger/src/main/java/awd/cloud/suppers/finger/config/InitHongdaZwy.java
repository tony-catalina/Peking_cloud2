package awd.cloud.suppers.finger.config;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

public class InitHongdaZwy {
	
	static boolean g_InitOk = false;
	public static CGals gals;
	public static CIDFpr idfpr;
    public interface CGals extends StdCallLibrary {
    	 
		int	 LIVESCAN_Init();
    	int  LIVESCAN_Close();
    	int  LIVESCAN_GetChannelCount();
    	int  LIVESCAN_SetBright(int nChannel, int nBright);
    	int  LIVESCAN_SetContrast(int nChannel, int nContrast);
    	int  LIVESCAN_GetBright(int nChannel, IntByReference pnBright);
    	int  LIVESCAN_GetContrast(int nChannel, IntByReference pnContrast);
    	int  LIVESCAN_GetMaxImageSize(int nChannel, IntByReference pnWidth, IntByReference pnHeight);
		int  LIVESCAN_GetCaptWindow(int nChannel, IntByReference pnOriginX, IntByReference pnOriginY,	IntByReference pnWidth, IntByReference pnHeight);
		int  LIVESCAN_SetCaptWindow(int nChannel, int nOriginX, int nOriginY,int nWidth, int nHeight);
   		int  LIVESCAN_Setup();
    	int  LIVESCAN_BeginCapture(int nChannel);
    	int  LIVESCAN_GetFPRawData(int nChannel, byte[] pRawData);
		int  LIVESCAN_GetFPBmpData(int nChannel, byte[] pBmpData);
   		int  LIVESCAN_EndCapture(int nChannel);
    	int  LIVESCAN_IsSupportCaptWindow(int nChannel);
    	int  LIVESCAN_IsSupportSetup();
		int  LIVESCAN_GetPreviewImageSize(int nChannel, IntByReference pnWidth,IntByReference pnHeight);
		int  LIVESCAN_GetPreviewData(int nChannel, byte[] pRawData);
		int  LIVESCAN_IsSupportPreview ();
   		int  LIVESCAN_GetVersion();
    	int  LIVESCAN_GetDesc(byte[] pszDesc);//max 1024 bytes
		int  LIVESCAN_GetErrorInfo(int nErrorNo, byte[] pszErrorInfo);//256 bytes
		int  LIVESCAN_SetVideoWindow (Pointer hWnd);
		int  LIVESCAN_SetBufferEmpty( byte []pImageData,int imageLength);
		
		int  LIVESCAN_InitEx(int nDevIndex);
		int  LIVESCAN_SetVideoWindowEx(int hWnd, int nLeft, int nTop, int nWidth, int nHeight);
		int  LIVESCAN_ChangePalette(int nStart, int nLen, byte[] lppe);
		int  LIVESCAN_CaptureFPBmpData(int nChannel,  byte	[]pRawData, int nTimeout);
		int  LIVESCAN_RawToBmp(byte[] pRaw, int nWidth, int nHeight, byte[] pBmpBuf, int nBufLen);
		int  LIVESCAN_BmpToRaw(byte[] pBmpBuf, int nBufLen, byte[] pRaw, int nRawLen, IntByReference pnWidth, IntByReference pnHeight);
    }	
    public interface CIDFpr extends StdCallLibrary{
		int FP_GetVersion(byte []code); // 4 bytes
		int FP_Begin();
		int FP_FeatureExtract(byte cScannerType, byte cFingerCode, byte[] pFingerImgBuf, byte[] pFeatureData);
		int FP_FeatureMatch(byte[] pFeatureData1, byte[] pFeatureData2, FloatByReference pfSimilarity); 
		int FP_ImageMatch(byte[] pFingerImgBuf, 	byte[] pFeatureData, FloatByReference pfSimilarity); 
		int FP_Compress(byte cScannerType,  byte cEnrolResult, byte cFingerCode, byte[] pFingerImgBuf, int nCompressRatio, byte[]pCompressedImgBuf, byte[] strBuf); //strBuf  256 bytes
		int FP_Decompress(byte[] pCompressedImgBuf,	byte[] pFingerImgBuf, byte[] strBuf); //strBuf 256 bytes
		int FP_GetQualityScore(byte[] pFingerImgBuf, ByteByReference pnScore); 
		int FP_GenFeatureFromEmpty1(byte cScannerType, byte cFingerCode, byte[] pFeatureData);
		int FP_GenFeatureFromEmpty2(byte cFingerCode, byte[] pFeatureData);
		int FP_End();
    }
    
	public static String init() {
		System.err.println("---init---");
		gals = (CGals)Native.loadLibrary("C:/Windows/ID_FprCap.dll", CGals.class);
		idfpr = (CIDFpr)Native.loadLibrary("C:/Windows/ID_Fpr.dll", CIDFpr.class);
		//new InitZwy();
		int re = initBegin();
		//ID_Fpr.FP_Begin();//调用算法
		if (re == 1) {
			return "初始化插件成功";
		}
		return "初始化插件失败";
	}
	

	public static int initBegin() {
		if (g_InitOk) return 1;
		int nRet = gals.LIVESCAN_Init();
		System.err.println("启动插件------"+(nRet == 1?"成功":"出错"));
    	g_InitOk = nRet >= 0;
    	idfpr.FP_Begin();
		//ID_Fpr.FP_Begin();
    	return nRet;
	}
	
	public static int initClose() {
		if (!g_InitOk) return 1;
		int nRet = gals.LIVESCAN_Close();
		System.err.println("停止插件------"+(nRet == 1?"成功":"出错"));
    	idfpr.FP_End();
    	g_InitOk = false;
		//ID_Fpr.FP_End();
    	return nRet;
	}
	
	public static void main(String[] args) {
		init();
	}
}
