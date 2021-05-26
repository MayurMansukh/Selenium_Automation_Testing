package com.bridgelabz.testcase;

import com.bridgelabz.base.Base;
import com.bridgelabz.pages.LoginPage;
import com.bridgelabz.pages.SignOutPage;
import com.bridgelabz.utils.ExcelUtilsBase;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.Test;
import java.io.IOException;

public class Amazon_Login_DataSet extends Base {
    @Test
    public static void Amazon_Login_With_DataSet() throws IOException {
        LoginPage loginObj = new LoginPage(driver);
        SignOutPage signOutPage = new SignOutPage(driver);
        ExcelUtilsBase excelBase = new ExcelUtilsBase();
        boolean check;

        XSSFSheet sheet = excelBase.excel_utils_base().getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            System.out.println("test started with data " + i);
            loginObj.click_signin_btn();
            String username1 = String.valueOf(sheet.getRow(i).getCell(0));
            String password1 = String.valueOf(sheet.getRow(i).getCell(1));

            check = loginObj.login(username1, password1);
             if(check) {
                 check = signOutPage.click_signOut_btn();
                 if (check) {
                     System.out.println("test sucess with data " + i);
                     driver.get("https://www.amazon.in/");
                 }
             }
            else {
                System.out.println("test failed with data " + i);
                driver.get("https://www.amazon.in/");
            }
        }

    }
}
