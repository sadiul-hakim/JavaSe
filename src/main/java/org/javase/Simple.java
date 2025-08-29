import java.lang.management.ManagementFactory;

void main() throws UnknownHostException {

    println("=======================Runtime======================");
    println("Processors " + Runtime.getRuntime().availableProcessors());
    println("Free memory " + Runtime.getRuntime().freeMemory());
    println("Max Memory " + Runtime.getRuntime().maxMemory());
    println("Total Memory " + Runtime.getRuntime().totalMemory());
    println("Version " + Runtime.version().feature());
    println("=======================Runtime======================");
    println("=======================System======================");
    println("Java Version Full: " + System.getProperty("java.version"));
    println("Java Vendor: " + System.getProperty("java.vendor"));
    println("JVM Name: " + System.getProperty("java.vm.name"));
    println("JVM Version: " + System.getProperty("java.vm.version"));
    println("JVM Vendor: " + System.getProperty("java.vm.vendor"));

    println("OS Name: " + System.getProperty("os.name"));
    println("OS Version: " + System.getProperty("os.version"));
    println("OS Architecture: " + System.getProperty("os.arch"));
    println("User Name: " + System.getProperty("user.name"));
    println("User Home: " + System.getProperty("user.home"));
    println("User Dir: " + System.getProperty("user.dir"));
    println("File Encoding: " + System.getProperty("file.encoding"));
    println("Default Charset: " + java.nio.charset.Charset.defaultCharset());
    println("=======================System======================");

    println("=======================Address======================");
    println("IPv4 Address: " + Inet4Address.getLocalHost().getHostAddress());
    println("Host Name (v4): " + Inet4Address.getLocalHost().getHostName());

    try {
        InetAddress localHost = InetAddress.getLocalHost();
        println("Canonical Host Name: " + localHost.getCanonicalHostName());
        println("Loopback Address: " + InetAddress.getLoopbackAddress().getHostAddress());

        NetworkInterface ni = NetworkInterface.getByInetAddress(localHost);
        if (ni != null) {
            byte[] mac = ni.getHardwareAddress();
            if (mac != null) {
                StringBuilder macStr = new StringBuilder();
                for (byte b : mac) {
                    macStr.append(String.format("%02X:", b));
                }
                println("MAC Address: " + macStr.substring(0, macStr.length() - 1));
            }
        }
    } catch (Exception e) {
        println("Network info error: " + e);
    }
    println("=======================Address======================");
    println("=======================ENV======================");

    System.getenv().forEach((k, v) -> println(k + " = " + v));
    println("=======================ENV======================");
    println("=======================ManagementFactory======================");

    println("Available Memory: " + ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed());
    println("Thread Count: " + ManagementFactory.getThreadMXBean().getThreadCount());
    println("OS Info from MXBean: " + ManagementFactory.getOperatingSystemMXBean().getName());
    println("=======================ManagementFactory======================");
    println("=======================System Time======================");


    println("Current Time (ms): " + System.currentTimeMillis());
    println("Nano Time: " + System.nanoTime());
    println("Uptime (ms): " + java.lang.management.ManagementFactory.getRuntimeMXBean().getUptime());
    println("Start Time (ms): " + java.lang.management.ManagementFactory.getRuntimeMXBean().getStartTime());
    println("=======================System Time======================");
}