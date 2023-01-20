package com.enchere.appli.controller;


import com.enchere.appli.dao.V_statDAO;
import com.enchere.appli.model.ObjectReturn;
import com.enchere.appli.model.V_stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("statistiques")
public class V_statController
{
    @GetMapping
    public ObjectReturn getStat(@RequestParam(name = "hash", required = true) String hash) throws Exception
    {

        ObjectReturn data = null;
        try
        {
            V_stat stat = V_statDAO.getAllStat();
            data = new ObjectReturn();
            data.setData(stat);
            data.setMessage("Succes");
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw e;
        }
        return data;
    }


}
