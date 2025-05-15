module aclcbukidnon.com.javafxactivity {
    requires javafx.controls;
    requires javafx.fxml;


    opens aclcbukidnon.com.javafxactivity to javafx.fxml;
    opens aclcbukidnon.com.javafxactivity.controllers to javafx.fxml;
    exports aclcbukidnon.com.javafxactivity;
}