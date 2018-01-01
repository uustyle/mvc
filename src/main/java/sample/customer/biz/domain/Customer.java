package sample.customer.biz.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer implements java.io.Serializable {

    private int id;

    @NotNull
    @Size(max = 20)
    private String name;

//    @NotNull
//    @Pattern(regexp = ".+@.+")
//    private String emailAddress;
//
//    @NotNull
//    @DateTimeFormat(pattern = "yyyy/MM/dd")
//    private Date birthday;
//
//    @Max(9)
//    @Min(0)
//    private Integer favoriteNumber;
//
//    @AssertFalse(message = "{errors.emailAddress.ng}")
//    public boolean isNgEmail() {
//        if (emailAddress == null) {
//            return false;
//        }
//        // ドメイン名が「ng.foo.baz」であれば使用不可のアドレスと見なす
//        return emailAddress.matches(".*@ng.foo.baz$");
//    }

    public Customer() {}

    public Customer(String name) {
        this.name = name;
    }

//    public Customer(String name, String emailAddress,
//            Date birthday, Integer favoriteNumber) {
//this.name = name;
//this.emailAddress = emailAddress;
//this.birthday = birthday;
//this.favoriteNumber = favoriteNumber;
//}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    @Override
    public String toString() {
        return String.format(
                "Customer [id=%s, name=%s]",
                id, name);
    }

    private static final long serialVersionUID = 5498108629060769963L;
}
