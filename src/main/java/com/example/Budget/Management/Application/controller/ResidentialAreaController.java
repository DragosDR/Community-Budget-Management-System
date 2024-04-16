package com.example.Budget.Management.Application.controller;

import com.example.Budget.Management.Application.entity.Admin;
import com.example.Budget.Management.Application.entity.residentUser;
import com.example.Budget.Management.Application.service.ResidentialAreaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/homepage")
public class ResidentialAreaController {

    private ResidentialAreaService residentialAreaService;
    @Autowired
    public ResidentialAreaController(ResidentialAreaService residentialAreaService){
        this.residentialAreaService=residentialAreaService;
    }


    /*
    GetMapping
     */
    @GetMapping("/welcome")
    public String Welcome() {
        return "\t Hello, Community Member! \n" +
                "Welcome to the Residential Budget Management Application! \n\n" +
                "By utilizing this application, you acknowledge and agree to our terms and conditions. \n" +
                "\t Important Reminder:" +
                "Never disclose your personal details, including your username and password, to anyone. \n" +
                "For documentation purposes, please refer to our Rules and Terms & Conditions located at /RulesT&C. \n" +
                "To proceed, please log in and navigate to /logged." +
                "Thank you for your cooperation and enjoy your experience with the app!";
    }


    @GetMapping("/RulesT&C")
    public String rulesOfResidentArea(){
        return " Welcome to Residential Budget Management Application!\n" +
                "\n" +
                "We are delighted to have you as a part of our community. " +
                "Before you begin exploring and utilizing our services, it's essential to familiarize yourself with the rules and guidelines that govern our platform. " +
                "By using our application, you accept the rules, that ensure a safe and enjoyable experience for everyone.\n" +
                "We use our default currency - RON - \n" +
                "\n" +
                "Rules and Terms & Conditions:\n" +
                "\n\n" +
                "For Users:\n" +
                "\n" +
                "Confidentiality of Personal Details: Your privacy and security are of utmost importance to us. " +
                "\t Please refrain from sharing your confidential details, such as your username, password, or any other sensitive information, with anyone." +
                " Keeping your account credentials secure helps protect your data and prevents unauthorized access.\n" +
                "\n" +
                "Management of Locked Funds: As a user, you have the authority to manage your locked funds within the application." +
                " You are solely responsible for filling and confirming your unlockable funds. " +
                "However, please note that only the admin has the authority to withdraw funds from your account. " +
                "Please exercise caution and diligence when managing your funds.\n" +
                "\n" +
                "\tCompliance with Admin Guidelines: \n " +
                "It's crucial to comply with the guidelines provided by the admin regarding fund management and other platform-related activities." +
                "Exceeding this overdraft limit would result in a notification or warning to the user, informing them that their account has reached or surpassed the standard overdraft limit." +
                " This notification serves as a reminder to users to manage their finances responsibly and take necessary actions to rectify the situation, such as depositing funds into their account to bring it back to a positive balance." +
                "Furthermore, it's crucial for users to be aware of the overdraft limit to avoid incurring additional fees or penalties associated with overdrawing their accounts beyond the established limit. " +
                "By adhering to the standard overdraft limit, users can maintain financial stability and avoid potential consequences related to overdrafts. " +
                "Overall, understanding and adhering to the standard overdraft limit is an important aspect of responsible account management within the Residential Budget Management platform. \n\n" +
                " If you have any questions or concerns, feel free to reach out to the admin for clarification and guidance.\n" +
                "\n" +
                "For Admins:\n" +
                "\n" +
                "Authority in Fund Management: As an admin, you have the guide and authority to oversee fund management activities within the application." +
                " You are responsible for ensuring the integrity and security of user accounts and funds.\n" +
                "\n" +
                "Withdrawal of Funds:" +
                " Admins have the exclusive authority to withdraw funds from user Main-accounts for designated purposes, such as: bill pay, area maintenance purposes, development and activities." +
                " Please use this authority judiciously and transparently, keeping the best interests of the community in mind.\n" +
                "\n" +
                "Utilization of General Funds: " +
                "Admins have control over the utilization of general funds to compensate for residential area purposes and development initiatives. " +
                "It's essential to prioritize the needs of the community and make informed decisions that contribute to its growth and well-being.";
    }

    @GetMapping("/logged")
    public String home(Principal principal){
        return "Hello, You logged in as a : " + principal.getName();
    }


    @GetMapping("/getAllResidentUsers")
    public ResponseEntity<?> getAllResidentUsers() {
        return residentialAreaService.getAllResidentUsers();
    }


    @GetMapping("/getAllAdmins")
    public ResponseEntity<?> getAllAdmins(){
        return residentialAreaService.getAllAdmins();
    }


    @GetMapping("/resident/users/{id}")
    public ResponseEntity<?> getResidentUsersAfterID(@PathVariable Long id){
        return residentialAreaService.getResidentUsersAfterID(id);
    }

    @GetMapping("/residentUserBalance/{id}")
    public ResponseEntity<?> getResidentUserBalanceAfterID(@PathVariable String id){
        return residentialAreaService.getResidentUserBalanceAfterID(id);
    }









    /*
    PostMapping
     */

    //add-resident-user
    @PostMapping("/addResidentUser")
    public ResponseEntity<?> addResidentUser(@RequestBody @Valid residentUser residentUser) {
        return residentialAreaService.addResidentUser(residentUser);
    }

//add-admin

    @PostMapping("/addAdmin")
    public ResponseEntity<?> addAdmin(@RequestBody @Valid Admin admin){
        return residentialAreaService.addAdmin(admin);
    }


    //add-money-to-locked-funds
    @PostMapping("/addLockedFunds")
    public ResponseEntity<?> addMoneyToLockedFunds(@RequestParam Long id,
                                                   @RequestParam Double amount){
        return residentialAreaService.addMoneyToLockedFunds(id,amount);
    }


    //send-locked-funds-to-main-account

    @PostMapping("/unlockFunds")
    public ResponseEntity<?> sendLockedFundsToPublicAccount(@RequestParam Long id,
                                                            @RequestParam Double amount) {
        return residentialAreaService.sendLockedFundsToPublicAccount(id, amount);
    }


    /*
    PutMapping
     */

    //resident-update
    @PutMapping("/updateResident/{id}")
    public ResponseEntity<?> updateResidentUser(@PathVariable Long id,
                                                @RequestBody @Valid residentUser residentUser) {
        return residentialAreaService.updateResidentUser(id, residentUser);
    }

    //admin - update
    @PutMapping("/updateAdmin/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable String id,
                                         @RequestBody Admin admin){
        return residentialAreaService.updateAdmin(id,admin);
    }

    //admin-withdraw-funds
    @PutMapping("/adminWithdraw")
    public ResponseEntity<?> adminWithdraw(@RequestParam Long id,
                                           @RequestParam String idAdmin,
                                           @RequestParam Double amount){
        return residentialAreaService.adminWithdraw(id,amount,idAdmin);
    }


    /*
    DeleteMapping
     */
    //delete-resident-user-by-id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteResidentUserById(@PathVariable Long id) {

        return residentialAreaService.deleteResidentUserById(id);
    }



}

