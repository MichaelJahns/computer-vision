import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;

import static com.sun.jna.win32.W32APIOptions.DEFAULT_OPTIONS;

public class JNAstab {
    public interface User32 extends StdCallLibrary {
        User32 instance = Native.load("user32", User32.class, DEFAULT_OPTIONS);

        // interface WNDENUMPROC extends StdCallCallback {
        //      boolean callback(Pointer hWnd, Pointer arg);
        //  }

        // boolean EnumWindows(WNDENUMPROC lpEnumFunc, Pointer arg);

        boolean ShowWindow(WinDef.HWND hWnd, int nCmdShow);

        boolean SetForegroundWindow(WinDef.HWND hWnd);

        WinDef.HWND FindWindow(String winClass, String title);

        int SW_SHOW = 1;

    }

    public static void main(String[] args) {
        User32 user32 = User32.instance;
        WinDef.HWND hWnd = user32.FindWindow(null, "Untitled - Paint");
        user32.ShowWindow(hWnd, User32.SW_SHOW);
        user32.SetForegroundWindow(hWnd);

    }

}
