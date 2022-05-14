package java.com.demo1.jooletest.Entity;

import javax.persistence.*;

@Entity
@Table(name="ProductInfo")
public class ProductInfo {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="productInfo_id")
    private Integer productInfo_id;


    @OneToOne(mappedBy="productInfo",
            cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.REFRESH})
    private Product product;

    @Column(name="product_details")
    private String product_details;

    @Column(name="contact")
    private String contact;

    @Column(name="documentation")
    private String documentation;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductInfo() {

    }

    public ProductInfo(String product_details, String contact,String documentation) {
        this.product_details = product_details;
        this.contact = contact;
        this.documentation = documentation;
    }

    public int getId() {
        return productInfo_id;
    }

    public void setId(int productInfo_id) {
        this.productInfo_id = productInfo_id;
    }

    public String getProductdetails() {
        return product_details;
    }

    public void setProductdetails(String product_details) {
        this.product_details = product_details;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }



}
