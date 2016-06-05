package in.healthycoder.tnea.college;

import java.util.Objects;

/**
 * Created by Suresh_Karuppannan on 5/22/2016.
 */
public class College {
    private District district;
    private String code;
    private String name;

    public static final String HEADERS = "DistCode,District,College Code,College Name";

    public College(String name, String code, District district) {
        this.name = Objects.requireNonNull(name, "name");
        this.code = Objects.requireNonNull(code, "code");
        this.district = Objects.requireNonNull(district, "district");
    }

    public College(String csvString) {
        String[] split = csvString.split(",");
        this.name = split[3];
        this.code = split[2];
        this.district = District.getValue(Integer.valueOf(split[0]));
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public District getDistrict() {
        return district;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", district.getCode(), district.getName(), code, name);
    }
}
