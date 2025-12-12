package asembly.utils;

import java.util.UUID;

public class GeneratorId {
    public static String generateId()
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0,8);
    }
}
