package in.healthycoder.tnea.college;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by Suresh_Karuppannan on 5/23/2016.
 */
public enum District {
    ARIYALUR(1),
    CHENNAI(2),
    COIMBATORE(3),
    CUDDALORE(4),
    DHARMAPURI(5),
    DINDIGUL(6),
    ERODE(7),
    KANCHEEPURAM(8),
    KANNIYAKUMARI(9),
    KARUR(10),
    KRISHNAGIRI(11),
    MADURAI(12),
    NAGAPATTINAM(13),
    NAMAKKAL(14),
    PERAMBALUR(15),
    PUDUKKOTTAI(16),
    RAMANATHAPURAM(17),
    SALEM(18),
    SIVAGANGA(19),
    THANJAVUR(20),
    THE_NILGIRIS(21),
    THENI(22),
    THIRUVALLUR(23),
    THIRUVANNAMALAI(24),
    THIRUVARUR(25),
    TIRUNELVELI(26),
    TIRUPPUR(27),
    TRICHIRAPPALLI(28),
    TUTICORIN(29),
    VELLORE(30),
    VILLUPURAM(31),
    VIRUDHUNAGAR(32);

    private int code;

    District(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return this.toString();
    }

    public static District getValue(int code) {
        return values()[code - 1];
    }

    public static Stream<District> stream() {
        return Arrays.stream(District.values());
    }

}
