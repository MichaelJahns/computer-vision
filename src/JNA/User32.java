package JNA;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;

import static com.sun.jna.win32.W32APIOptions.DEFAULT_OPTIONS;

public interface User32 extends com.sun.jna.platform.win32.User32 {
    User32 instance = Native.load("user32", User32.class, DEFAULT_OPTIONS);

    boolean ShowWindow(WinDef.HWND hWnd, int nCmdShow);

    boolean SetForegroundWindow(WinDef.HWND hWnd);

    boolean CloseWindow(WinDef.HWND hWnd);

    boolean DestroyWindow(WinDef.HWND hWnd);

    WinDef.HWND FindWindow(String winClass, String title);

    int SW_SHOW = 1;
}
