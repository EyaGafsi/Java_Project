module org.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires jakarta.persistence;
    requires static lombok;
    requires mysql.connector.j;
    requires org.hibernate.orm.core;
    requires jdk.jdi;
    // Open the package to Hibernate for reflective access to private fields
    opens org.example.demo1 to javafx.fxml, org.hibernate.orm.core;
    opens org.example.demo1.entities to org.hibernate.orm.core;
    opens org.example.demo1.dao to org.hibernate.orm.core;

    // Export the package so other modules can access it
    exports org.example.demo1;
    exports org.example.demo1.views;
    opens org.example.demo1.views to javafx.fxml, org.hibernate.orm.core;
}
