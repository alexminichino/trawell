package com.trawell.controllers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import com.trawell.models.Ad;
import com.trawell.models.Agency;
import com.trawell.models.Photo;
import com.trawell.models.User;
import com.trawell.services.AdService;
import com.trawell.services.AgencyService;
import com.trawell.services.PhotoService;
import com.trawell.utilities.Encoder;

import com.trawell.utilities.uploader.UploadUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Mario Paone AdController: andranno mappate tutte le funzionalità
 *         relative agli annunci ed i relativi controller
 * 
 */

@Controller
@RequestMapping("agency")
public class AdController {
    @Autowired
    private AdService adDao;

    @Autowired
    private AgencyService agencyDao;

    @Autowired
    private PhotoService photoDao;

    /**
     * @author Mario Paone Method checks if the user is already logged and if he is
     *         an agency
     * 
     * @param session
     * @return true if he is an agency, false otherwise
     */
    private boolean isAgency(HttpSession session) {
        return session.getAttribute("user") instanceof Agency;
    }

    @GetMapping("/home")
    public String landing(HttpSession session) {
        return isAgency(session) ? "pages/agency/home" : "pages/user/home";
    }

    @GetMapping("/createadvertisement")
    public String create_ad_page(HttpSession session) {
        return isAgency(session) ? "pages/agency/createadvertisement" : "pages/user/login";
    }

    /**
     * @author Mario Paone The method allows the agency to create an Advertisement
     * @param payment_method the payment method choosed by the agency
     * @param time_choosed   the period of time for the ad
     * @param starting_time  the date when the advertisement will start
     * @param session
     * @param model
     * @return return the home page of the web site
     * @throws ParseException
     */
    @PostMapping("/createAd")
    public String createAd(@RequestParam(name = "payment_method", required = true) String payment_method,
            @RequestParam(name = "ad_time", required = true) String time_choosed,
            @RequestParam(name = "starting_time", required = true) Date starting_time,
            @RequestParam(name = "file", required = true) MultipartFile file, HttpSession session, Model model)
            throws ParseException {

        // Imposta la durata dell'ad e il costo
        long costo = 0;
        int mesi = 0;
        if (time_choosed.equals("1mesi")) {
            costo = 90;
            mesi = 1;
        } else if (time_choosed.equals("3mesi")) {
            costo = 250;
            mesi = 3;
        }
        if (time_choosed.equals("6mesi")) {
            costo = 460;
            mesi = 6;
        }
        if (time_choosed.equals("12mesi")) {
            costo = 860;
            mesi = 12;
        }
        User user = (User) session.getAttribute("user");

        // Calcola la data di fine dell'ad
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cal.setTime(sdf.parse(starting_time.toString()));
        cal.add(Calendar.MONTH, mesi);
        Date finaldate = new Date(cal.getTimeInMillis());

        // Calcola il path dell'immagine
        Photo photo = new Photo();

        Encoder encoder = new Encoder();

        String uploadDir = encoder.encoding(user.getMail(), 3);
        String fileName = UploadUtils.getCurrentTimeUsingDate();
        photo.setPath(UploadUtils.uploadPhoto(file, uploadDir, fileName));
        photo.setPost(null);

        Photo savedPhoto = photoDao.create(photo);

        Ad ad = new Ad();
        ad.setAdPaymentMethod(payment_method);
        ad.setIdOwner(user.getId());
        ad.setAdCost(costo);
        ad.setAdStartingDate(starting_time);
        ad.setAdDueDate(finaldate);
        ad.setIdPhoto(Long.toString(savedPhoto.getId()));
        adDao.create(ad);
        model.addAttribute("msg", "Inserzione aggiunta con successo");
        return "pages/agency/home";

    }

    @GetMapping("/deleteAdsLandAg")
    public String deleteadsag(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");

        if (isAgency(session)) {
            Collection<Ad> listaAd = adDao.findAll();
            ArrayList<Ad> listaMyAds = new ArrayList<Ad>();
            if (listaAd.size() > 0) {
                for (Ad ad : listaAd) {
                    if (ad.getIdOwner() == user.getId()) {
                        listaMyAds.add(ad);
                    }
                }
                model.addAttribute("listaAds", listaMyAds);
            }
            return "pages/agency/deletemyad";
        }

        return "pages/user/home";

    }


    @PostMapping("/deleteAd")
    public String createAd(@RequestParam(name = "id", required = true) Long idAd, HttpSession session, Model model)
            throws ParseException {
                try{
                    adDao.delete(idAd);
                }catch (Exception e) {
                    
                }
                return "pages/agency/home";

    }
}
