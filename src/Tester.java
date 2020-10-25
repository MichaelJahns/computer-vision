import com.sun.jna.Native;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinDef;

public class Tester {


    public static void main(String args[]) {
        run();
    }


    public static void run() {
        WinDef.HWND windowHandle = Win32.findWindowHandle();
        Win32.moveToFront(windowHandle);
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            boolean wasSuccess = Win32.destroyWindow(windowHandle);
            if (!wasSuccess) {
                int errorCode = Native.getLastError();
                System.out.println("error: " + errorCode);
                throw new Win32Exception(errorCode);
            }
        }
    }

}
