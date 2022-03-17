package com.furminger.allaboutclovelly;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainViewModel extends ViewModel {

    private PointOfInterest visitorsCentre = new PointOfInterest(1, 50.99922758761011, -4.40493487281753,
            "Visitors Centre",
            "The visitors centre is where .......",
            new ArrayList<Drawable>());
    private PointOfInterest donkeyStables = new PointOfInterest(2, 50.999443650702425, -4.402059544653397,
            "Donkey Stables",
            "The donkey stables at Clovelly are almost as old as the village itself.",
            new ArrayList<Drawable>());
    private PointOfInterest fishermansCottage = new PointOfInterest(3, 50.99795648517228, -4.399230743006255,
            "Fisherman's Cottage",
            "Inside the " +
                    "cottage you can see how a Clovelly fisherman and his family lived in the 1930s. The " +
                    "parlour is decorated with domestic treasures of the period, including simple cottage " +
                    "furniture, colourful pictures and religious engravings. The tiny kitchen is plain but " +
                    "full of period charm. Upstairs there are two small bedrooms, a sail loft, and an attic " +
                    "complete with straw mattresses.",
            new ArrayList<Drawable>());
    private PointOfInterest redLionHotel = new PointOfInterest(4, 50.99907263622343, -4.397884284339087,
            "Red Lion Hotel",
            "The Red Lion Hotel is an 18th Century 4-star Inn that stands on the quay alongside Clovelly’s " +
                    "ancient harbour.",
            new ArrayList<Drawable>());
    private PointOfInterest RNLILifeboatStation = new PointOfInterest(5, 50.99836498982892, -4.397444391999562,
            "RNLI Lifeboat Station",
            "Following " +
                    "a terrible storm Clovelly’s first lifeboat station was built in 1870. Most of the " +
                    "fishing fleet was destroyed with the loss of many lives. At only 33 feet long and " +
                    "built of wood, the lifeboat was powered through the waves by a crew of sturdy rowers.",
            new ArrayList<Drawable>());
    private PointOfInterest clovellyCourtGardens = new PointOfInterest(6, 51.000409170390604, -4.4104924096382225,
            "Clovelly Court Gardens",
            "Charming walled garden, a few minutes drive from the Clovelly village car park .",
            new ArrayList<Drawable>());

    private Map<String,PointOfInterest> pointsOfInterest = new HashMap<>();

    public PointOfInterest getPointOfInterest(String key) {
        return pointsOfInterest.get(key);
    }

    /**
     * This method adds the photos into the PointsOfInterest HashMap
     */
    private void addPhotosToPointsOfInterest() {

        Drawable drawable = Resources.getSystem().getDrawable(R.drawable.visitorcentre, null);
        PointOfInterest poi = getPointOfInterest("Visitors Centre");
        poi.addPlacePhoto(drawable);

        drawable = Resources.getSystem().getDrawable(R.drawable.donkeystables, null);
        poi = getPointOfInterest("Donkey Stables");
        poi.addPlacePhoto(drawable);

        drawable = Resources.getSystem().getDrawable(R.drawable.fishermanscottage, null);
        poi = getPointOfInterest("Fisherman's Cottage");
        poi.addPlacePhoto(drawable);

        drawable = Resources.getSystem().getDrawable(R.drawable.redlionhotel, null);
        poi = getPointOfInterest("Red Lion Hotel");
        poi.addPlacePhoto(drawable);
        drawable = Resources.getSystem().getDrawable(R.drawable.redlioninnoldback, null);
        poi.addPlacePhoto(drawable);

        drawable = Resources.getSystem().getDrawable(R.drawable.rnli, null);
        poi = getPointOfInterest("RNLI Lifeboat Station");
        poi.addPlacePhoto(drawable);

        drawable = Resources.getSystem().getDrawable(R.drawable.clovellycourtgardens, null);
        poi = getPointOfInterest("Clovelly Court Gardens");
        poi.addPlacePhoto(drawable);

    }
}
