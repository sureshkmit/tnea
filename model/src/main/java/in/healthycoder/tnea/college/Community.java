package in.healthycoder.tnea.college;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by Suresh_Karuppannan on 5/23/2016.
 */
public enum Community {
    ST(1),
    SC(2),
    SCA(3),
    MBC(4),
    BC(5),
    BCM(6),
    OC(7);

    private int code;

    Community(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return toString();
    }

    public static Community getValue(int code) {
        return values()[code - 1];
    }

    public static Community getValue(String code) throws UnknownCommunityException {
        String trimmed = code.trim();
        return stream().filter(c -> c == Community.valueOf(trimmed))
                .findFirst()
                .orElseThrow(() -> new UnknownCommunityException(trimmed));
    }

    public static Stream<Community> stream() {
        return Arrays.stream(Community.values());
    }
}
