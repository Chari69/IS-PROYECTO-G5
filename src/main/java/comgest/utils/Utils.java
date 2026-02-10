package comgest.utils;

public class Utils {

    public static boolean isAdminRole(String role) {
        if (role == null) {
            return false;
        }
        String normalized = role.trim().toLowerCase();
        return normalized.equals("administrador");
    }

    public static String normalizar(String value) {
        return value == null ? "" : value.trim();
    }
}
