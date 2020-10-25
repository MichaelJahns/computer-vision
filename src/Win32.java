import JNA.Kernel32;
import JNA.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

public class Win32 {
    private static User32 user32 = User32.instance;
    private static Kernel32 kernel32 = Kernel32.instance;

    public static HWND findWindowHandle() {
        HWND hWnd = user32.FindWindow(null, "Ragnarok : Zero");
        if (hWnd == null) {
            System.out.println("Ragnarok is not running");
        }
        return hWnd;
    }

    public static void moveToFront(HWND windowHandle) {
        user32.ShowWindow(windowHandle, User32.SW_SHOW);
        user32.SetForegroundWindow(windowHandle);
    }

    public static void closeWindow(HWND windowHandle) {
        user32.CloseWindow(windowHandle);
    }

    public static boolean destroyWindow(HWND windowHandle) {
        boolean wasSuccess = user32.DestroyWindow(windowHandle);
        return wasSuccess;
    }

    public static int getLastError() {
        int errorCode = kernel32.GetLastError();
        return errorCode;
    }


}
