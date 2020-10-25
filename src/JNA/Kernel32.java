package JNA;

import com.sun.jna.Native;

import static com.sun.jna.win32.W32APIOptions.DEFAULT_OPTIONS;

public interface Kernel32 extends com.sun.jna.platform.win32.Kernel32 {
    Kernel32 instance = Native.load("kernel32", Kernel32.class, DEFAULT_OPTIONS);

    int GetLastError();
}
