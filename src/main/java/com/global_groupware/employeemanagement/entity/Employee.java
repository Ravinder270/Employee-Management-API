package com.global_groupware.employeemanagement.entity;

import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
public class Employee {

    @Id
    private String id;

    @NotBlank(message = "Employee name is required")
    private String employeeName;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "Phone number must be in the format xxx-xxx-xxxx")
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Nullable
    private String reportsTo;

    @Nullable
    private String profileImage;

    //constructor
    public Employee()
    {

    }

    //Constructor with mandatory fields
    public Employee(String employeeName, String phoneNumber, String email, String reportsTo) {
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.reportsTo = reportsTo;
    }

    //Constructor with all fields


    public Employee(String employeeName, String phoneNumber, String email, String reportsTo, @Nullable String profileImage) {
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.reportsTo = reportsTo;
        this.profileImage = profileImage;
    }

    //Getter and Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }

    @Nullable
    public String getProfileImage() {
        return (profileImage != null) ? profileImage : "No image found";
    }


    public void setProfileImage(@Nullable String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", reportsTo='" + reportsTo + '\'' +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }
}
