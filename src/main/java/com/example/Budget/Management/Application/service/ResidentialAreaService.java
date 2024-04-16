package com.example.Budget.Management.Application.service;

import com.example.Budget.Management.Application.entity.Account;
import com.example.Budget.Management.Application.entity.Admin;
import com.example.Budget.Management.Application.entity.adminAccount;
import com.example.Budget.Management.Application.entity.residentUser;
import com.example.Budget.Management.Application.repository.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidentialAreaService {
    private residentUserRepository residentUserRepository;
    private adminRepository adminRepository;
    private accountRepository accountRepository;
    private currencyRepository currencyRepository;
    private adminAccountRepository adminAccountRepository;
    private defCurrencyRepository defCurrencyRepository;


    public ResidentialAreaService(residentUserRepository residentUserRepository,
                                  adminRepository adminRepository,
                                  accountRepository accountRepository,
                                  currencyRepository currencyRepository,
                                  adminAccountRepository adminAccountRepository,
                                  defCurrencyRepository defCurrencyRepository) {

        this.residentUserRepository = residentUserRepository;
        this.adminRepository = adminRepository;
        this.accountRepository = accountRepository;
        this.currencyRepository = currencyRepository;
        this.adminAccountRepository = adminAccountRepository;
        this.defCurrencyRepository = defCurrencyRepository;
    }


    //Methods FOR GET
    public ResponseEntity<?> getAllResidentUsers() {
        ResponseEntity<?> response = null;


        List<residentUser> residentUserList = residentUserRepository.findAll();

        try {

            if (residentUserList != null && residentUserList.size() > 0) {
                response = new ResponseEntity<>(residentUserList, HttpStatus.OK);

            } else {
                response = new ResponseEntity<>("No users found.", HttpStatus.NOT_FOUND);
            }

        }catch (DataAccessException e) {
            return new ResponseEntity<>("Database access error: Unable to retrieve users", HttpStatus.SERVICE_UNAVAILABLE);

        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    //GET ALL ADMINS
    public ResponseEntity<?> getAllAdmins() {
        ResponseEntity<?> response = null;


        try {
            List<Admin> adminList = adminRepository.findAll();


            if (adminList != null && adminList.size() > 0) {

                response = new ResponseEntity<>(adminList, HttpStatus.OK);

            } else {
                response = new ResponseEntity<>("No admins found", HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e) {
            return new ResponseEntity<>("Database error: Unable to retrieve admins", HttpStatus.SERVICE_UNAVAILABLE);

        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }


    //GET USER AFTER ID
    public ResponseEntity<?> getResidentUsersAfterID (Long id) {
        ResponseEntity<?> response = null;

        Optional<residentUser> optionalResidentUser = residentUserRepository.findById(id);
        try {
            if (optionalResidentUser.isPresent()) {
                response = new ResponseEntity<>(optionalResidentUser.get(), HttpStatus.OK);

            } else {
                response = new ResponseEntity<>("The resident related to this ID : Not found ", HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e) {
            return new ResponseEntity<>("Database error: Unable to retrieve admins", HttpStatus.SERVICE_UNAVAILABLE);

        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    //GET BALANCE AFTER ID

    public ResponseEntity<?> getResidentUserBalanceAfterID(String id){
        ResponseEntity<?> response = null;

        Optional<Account> accountOptional = accountRepository.findById(id);
        try{
            if (!accountOptional.isPresent()){
                response = new ResponseEntity<>("The balance related to this ID : Not found",HttpStatus.NOT_FOUND);
            }else {
                response = new ResponseEntity<>(accountOptional.get(), HttpStatus.OK);
            }
        }catch (DataAccessException e) {
            return new ResponseEntity<>("Database error: Unable to retrieve admins", HttpStatus.SERVICE_UNAVAILABLE);

        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }















    //FOR POST MAPPING

    public  ResponseEntity<?> addResidentUser(residentUser residentUser){

        ResponseEntity<?> response = null;
        try {
            residentUser savedResidentUser = residentUserRepository.saveAndFlush(residentUser);


            response = new ResponseEntity<>(  savedResidentUser, HttpStatus.CREATED);


        }catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>("User data already present encrypted ID ->" + residentUser.getId().hashCode(), HttpStatus.BAD_REQUEST);

        }catch (DataAccessException e) {
            response = new ResponseEntity<>("Error accessing data from the database", HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (Exception e){
            response = new ResponseEntity<>("An unexpected error occurred",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }





    //addAdmin
    public ResponseEntity<?> addAdmin(Admin admin) {

        ResponseEntity<?> response = null;

        Admin insertedAdmin = adminRepository.saveAndFlush(admin);

        try {

            response = new ResponseEntity<>(insertedAdmin,HttpStatus.CREATED);

        }catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>("Admin data already present encrypted ID ->" + admin.getId().hashCode(), HttpStatus.BAD_REQUEST);

        }catch (DataAccessException e) {
            response = new ResponseEntity<>("Error accessing data from the database", HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (Exception e){
            response = new ResponseEntity<>("An unexpected error occurred",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }



    //UPDATE RESIDENT

    public ResponseEntity<?> updateResidentUser(Long id, residentUser residentUser) {
        ResponseEntity<?> response = null;

        Optional<residentUser> residentUserOptional = residentUserRepository.findById(id);

        try {

            if (residentUserOptional.isPresent()) {
                residentUser foundResidentUser = residentUserOptional.get();

                foundResidentUser.setFirstName(residentUser.getFirstName());
                foundResidentUser.setLastName(residentUser.getLastName());
                foundResidentUser.setPhoneNumber(residentUser.getPhoneNumber());
                foundResidentUser.setBlockNumber(residentUser.getBlockNumber());
                foundResidentUser.setApartmentNumber(residentUser.getApartmentNumber());

                residentUser updateResidentUser = residentUserRepository.saveAndFlush(foundResidentUser);

                response = new ResponseEntity<>(updateResidentUser, HttpStatus.OK);
            } else {
                
                response = new ResponseEntity<>("Details regarding this ID, not present in the database. " , HttpStatus.NOT_FOUND);
            }

        }catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>("Data integrity violation occurred", HttpStatus.BAD_REQUEST);

        }catch (DataAccessException e) {
            response = new ResponseEntity<>("Error accessing data from the database", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            response = new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }


    //UPDATE ADMIN
    public ResponseEntity<?> updateAdmin(String id, Admin admin) {
        ResponseEntity<?> response = null;

        Optional<Admin> adminOptional = adminRepository.findById(id);

        try {

            if (adminOptional .isPresent()) {
                Admin foundAdmin = (adminOptional.get());

                foundAdmin .setFirstName(admin.getFirstName());
                foundAdmin .setLastName(admin.getLastName());
                foundAdmin .setPhoneNumber(admin.getPhoneNumber());

                Admin updatedAdmin =adminRepository.saveAndFlush(foundAdmin );

                response = new ResponseEntity<>(updatedAdmin , HttpStatus.OK);
            } else {
                response = new ResponseEntity<>("Details regarding this ID, not present in the database. " , HttpStatus.NOT_FOUND);
            }

        }catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>("Data integrity violation occurred", HttpStatus.BAD_REQUEST);

        }catch (DataAccessException e) {
            response = new ResponseEntity<>("Error accessing data from the database", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            response = new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }








    //unlock funds to main-account
    public ResponseEntity<?> sendLockedFundsToPublicAccount (Long id,Double amount){
        ResponseEntity<?> response = null;

        Optional<residentUser> residentUserOptional = residentUserRepository.findById(id);

        try {

            if (!residentUserOptional.isPresent()) {
                response = new ResponseEntity<>("Funds: " + amount + " could not be processed, User not found", HttpStatus.NOT_IMPLEMENTED);

            } else {

                residentUser foundResidentUser = residentUserOptional.get();

                if (foundResidentUser.getLockedBalance() < amount) {
                    return new ResponseEntity<>("Transfer amount exceeds lockedBalance\n" +
                            "current lockedBalance: " + foundResidentUser.getLockedBalance(), HttpStatus.BAD_REQUEST);
                }
                Account updatedAccount = foundResidentUser.getAccount();


                Double newBalance = foundResidentUser.getLockedBalance() - amount;

                foundResidentUser.setLockedBalance(newBalance);


                updatedAccount.setMain_account( updatedAccount.getMain_account() + amount);


                residentUser updatedResidentAccount = residentUserRepository.saveAndFlush(foundResidentUser);

                response = new ResponseEntity<>("Funds: " + amount + ", were successfully added to Main-Account of: " + id + "\n" +
                        "\n Remaining lockedBalance funds: " + foundResidentUser.getLockedBalance() +
                        "\n Current Main-Account balance: " + updatedAccount.getMain_account(), HttpStatus.OK);
            }
        }catch (DataIntegrityViolationException e) {
            response = new ResponseEntity<>("Data integrity violation occurred", HttpStatus.BAD_REQUEST);

        } catch (DataAccessException e) {
            response = new ResponseEntity<>("Error accessing data from the database", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            response = new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }





    //addMoneyToLockedFunds AS a user!
    public ResponseEntity<?> addMoneyToLockedFunds(Long id, Double amount) {
        ResponseEntity<?> response = null;

        Optional<residentUser> residentUserOptional = residentUserRepository.findById(id);

        try {

            if (!residentUserOptional.isPresent()) {
                response = new ResponseEntity<>("The introduced amount: " + amount + " could not be processed, User not found", HttpStatus.NOT_FOUND);

            } else {
                residentUser foundResidentUser = residentUserOptional.get();


                Double newBalance = foundResidentUser.getLockedBalance() + amount;

                foundResidentUser.setLockedBalance(newBalance);

                residentUser updatedResidentAccount = residentUserRepository.saveAndFlush(foundResidentUser);

                response = new ResponseEntity<>("Amount: " + amount + ", has been added to ID: " + id + " LockedBalance \n" +
                        "Current LockedBalance: " + foundResidentUser.getLockedBalance(), HttpStatus.OK);

            }
        }catch (ConstraintViolationException e) {
            response = new ResponseEntity<>("Constraint violation: " + e.getMessage(), HttpStatus.BAD_REQUEST);

        }catch (SecurityException e) {
            response= new ResponseEntity<>("Not authorized",HttpStatus.UNAUTHORIZED);

        }catch (IllegalArgumentException e) {
            response = new ResponseEntity<>("Invalid input parameters", HttpStatus.BAD_REQUEST);

        } catch (DataAccessException e) {
            response = new ResponseEntity<>("Error accessing data from the database", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            response = new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }


    //admin WITHDRAWFUNDS
    public ResponseEntity<?> adminWithdraw (Long id, Double amount, String idAdmin) {
        ResponseEntity<?> response = null;


        Optional<residentUser> residentUserOptional = residentUserRepository.findById(id);
        Optional<Admin> adminOptional = adminRepository.findById(idAdmin);

        try {

            if (!residentUserOptional.isPresent() && !adminOptional.isPresent()) {
                response = new ResponseEntity<>("Details regarding User-ID or Admin-ID, not present in the database. ",HttpStatus.BAD_REQUEST);

            } else {

                residentUser foundResidentUser = residentUserOptional.get();

                Account updatedAccount = foundResidentUser.getAccount();

                Admin foundAdmin = adminOptional.get();

                adminAccount updatedAdminAccount =foundAdmin.getAdminAccount();


                Double newBalance = updatedAccount.getMain_account() - amount;

                if (newBalance < -250) {

                    return new ResponseEntity<>("The requested withdrawal amount surpasses the established Standard Overdraft Limits for account ID: " + id + "."+
                            " \nThe current balance after the attempted transaction is: " + updatedAccount.getMain_account() +
                            "\n\tReminder: Overdraft Limit ( -250 Ron ) "  , HttpStatus.BAD_REQUEST);
                }else {

                    updatedAccount.setMain_account(newBalance);

                    Double adminBalance = updatedAdminAccount.getCollected_funds() + amount;

                    updatedAdminAccount.setCollected_funds(adminBalance);
                }

                residentUser residentUserUpdated = residentUserRepository.saveAndFlush(foundResidentUser);
                adminAccount adminAccountUpdated = adminAccountRepository.saveAndFlush(updatedAdminAccount);

                response = new ResponseEntity<>(" Withdrawal Completed.\n " +
                        "ID's: " + id + ", Main-Account current balance: " + updatedAccount.getMain_account() +
                        "\n Admin collected funds: " + updatedAdminAccount.getCollected_funds() , HttpStatus.OK);
            }

        }catch (ConstraintViolationException e) {
            response = new ResponseEntity<>("Constraint violation: " + e.getMessage(), HttpStatus.BAD_REQUEST);

        } catch (SecurityException e) {
            response= new ResponseEntity<>("The withdrawn amount : " + amount + " could not be processed, key not authorized",HttpStatus.UNAUTHORIZED);

        }catch (IllegalArgumentException e) {
            response = new ResponseEntity<>("Invalid input parameters", HttpStatus.BAD_REQUEST);

        } catch (DataAccessException e) {
            response = new ResponseEntity<>("Error accessing data from the database", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            response = new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    //delete mapping
    public ResponseEntity<?> deleteResidentUserById(Long id) {
        ResponseEntity<?> response = null;

        Optional <residentUser> foundUser = residentUserRepository.findById(id);

        try {
            if (foundUser.isPresent()) {

                residentUserRepository.deleteById(id);

                response = new ResponseEntity<>("Successfully deleted from the database, User with id: " + id, HttpStatus.OK);

            } else {
                response = new ResponseEntity<>("This user is non-existent in the database.",HttpStatus.BAD_REQUEST);
            }
        }catch (ConstraintViolationException e) {
            response = new ResponseEntity<>("Constraint violation: " + e.getMessage(), HttpStatus.BAD_REQUEST);

        }catch (IllegalArgumentException e) {
            response = new ResponseEntity<>("Invalid input parameters", HttpStatus.BAD_REQUEST);

        } catch (DataAccessException e) {
            response = new ResponseEntity<>("Error accessing data from the database", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            response = new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }


}

