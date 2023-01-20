package com.enchere.appli.controller;

import com.enchere.appli.dao.AdminDAO;
import com.enchere.appli.exception.AuthentificationFailedException;
import com.enchere.appli.model.Admin;
import com.enchere.appli.model.ObjectReturn;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@CrossOrigin("*")
@RestController
@RequestMapping("admins")
public class AdminController
{
    @PostMapping
    public ObjectReturn login(@RequestBody Admin admin) throws Exception
    {
        ObjectReturn ans = null;
        Object[] data = null;
        try
        {
            System.out.println(admin.getUserName());
            data = AdminDAO.toLog(admin);
            ans = new ObjectReturn();
            ans.setData(data);
            ans.setMessage("Authentification success");

        }
        catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException e) {
            // TODO Auto-generated catch block
            throw e;
        }
        catch (AuthentificationFailedException e) {
            // TODO Auto-generated catch block
            throw e;
        }
        return ans;
    }
}
