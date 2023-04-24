package gui;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entities.Annonces;
import entities.Categorie;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import services.AnnonceService;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import services.CategorieService;

public class AjoutAnnonceController implements Initializable {

    @FXML
    private TextField tf_description;
    @FXML
    private TextField tf_adresse;
    @FXML
    private DatePicker picker_date;
    @FXML
    private ImageView imageadd;
    @FXML
    private ComboBox<String> combo;
    private CategorieListData categeListdata = new CategorieListData();
    private File file;
    private String lien = "";
    @FXML
    private Button frontBTN;
private String imagep ;



 private String[] badWords = {"2g1c",
 "2 girls 1 cup",
 "acrotomophilia",
 "ahole",
 "anal",
 "anilingus",
 "anus",
 "arsehole",
 "ash0le",
 "ash0les",
 "asholes",
 "ass",
 "Ass Monkey",
 "Assface",
 "asshole",
 "assmunch",
 "auto erotic",
 "autoerotic",
 "assh0le",
 "assh0lez",
 "asshole",
 "assholes",
 "assholz",
 "asswipe",
 "azzhole",
 "babeland",
 "baby batter",
 "ball gag",
 "ball gravy",
 "ball kicking",
 "ball licking",
 "ball sack",
 "ball sucking",
 "bangbros",
 "bareback",
 "barely legal",
 "barenaked",
 "bastardo",
 "bastinado",
 "bbw",
 "bdsm",
 "beaver cleaver",
 "beaver lips",
 "bestiality",
 "bi curious",
 "big black",
 "big breasts",
 "big knockers",
 "big tits",
 "bimbos",
 "birdlock",
 "bitch",
 "black cock",
 "blonde action",
 "blonde on blonde action",
 "blow j",
 "blow your l",
 "blue waffle",
 "blumpkin",
 "bollocks",
 "bondage",
 "boner",
 "boob",
 "boobs",
 "booty call",
 "brown showers",
 "brunette action",
 "bukkake",
 "bulldyke",
 "bullet vibe",
 "bung hole",
 "bunghole",
 "busty",
 "butt",
 "buttcheeks",
 "butthole",
 "bassterds",
 "bastard",
 "bastards",
 "bastardz",
 "basterds",
 "basterdz",
 "Biatch",
 "bitches",
 "Blow Job",
 "boffing",
 "butthole",
 "buttwipe",
 "camel toe",
 "camgirl",
 "camslut",
 "camwhore",
 "carpet muncher",
 "carpetmuncher",
 "chocolate rosebuds",
 "circlejerk",
 "cleveland steamer",
 "clit",
 "clitoris",
 "clover clamps",
 "clusterfuck",
 "cock",
 "cocks",
 "coprolagnia",
 "coprophilia",
 "cornhole",
 "cum",
 "cumming",
 "cunnilingus",
 "cunt",
 "c0ck",
 "c0cks",
 "c0k",
 "cawk",
 "cawks",
 "Clit",
 "cnts",
 "cntz",
 "cockhead",
 "cock-head",
 "CockSucker",
 "cock-sucker",
 "crap",
 "cunts",
 "cuntz",
 "darkie",
 "date rape",
 "daterape",
 "deep throat",
 "deepthroat",
 "dick",
 "dildo",
 "dirty pillows",
 "dirty sanchez",
 "dog style",
 "doggie style",
 "doggiestyle",
 "doggy style",
 "doggystyle",
 "dolcett",
 "domination",
 "dominatrix",
 "dommes",
 "donkey punch",
 "double dong",
 "double penetration",
 "dp action",
 "dild0",
 "dild0s",
 "dildos",
 "dilld0",
 "dilld0s",
 "dominatricks",
 "dominatrics",
 "dyke",
 "eat my ass",
 "ecchi",
 "ejaculation",
 "erotic",
 "erotism",
 "escort",
 "ethical slut",
 "eunuch",
 "enema",
 "faggot",
 "fecal",
 "felch",
 "fellatio",
 "feltch",
 "female squirting",
 "femdom",
 "figging",
 "fingering",
 "fisting",
 "foot fetish",
 "footjob",
 "frotting",
 "fuck",
 "fuck buttons",
 "fudge packer",
 "fudgepacker",
 "futanari",
 "f u c k",
 "f u c k e r",
 "fag",
 "fag1t",
 "faget",
 "fagg1t",
 "faggit",
 "fagit",
 "fags",
 "fagz",
 "faig",
 "faigs",
 "fart",
 "flipping the bird",
 "fucker",
 "fuckin",
 "fucking",
 "fucks",
 "fuk",
 "Fukah",
 "Fuken",
 "fuker",
 "Fukin",
 "Fukk",
 "Fukkah",
 "Fukken",
 "Fukker",
 "Fukkin",
 "g-spot",
 "gang bang",
 "gay sex",
 "genitals",
 "giant cock",
 "girl on",
 "girl on top",
 "girls gone wild",
 "goatcx",
 "goatse",
 "gokkun",
 "golden shower",
 "goo girl",
 "goodpoop",
 "goregasm",
 "grope",
 "group sex",
 "guro",
 "g00k",
 "gay",
 "gayboy",
 "gaygirl",
 "gays",
 "gayz",
 "God-damned",
 "hand job",
 "handjob",
 "hard core",
 "hardcore",
 "hentai",
 "hoe",
 "homoerotic",
 "honkey",
 "hooker",
 "hot chick",
 "how to kill",
 "how to murder",
 "huge fat",
 "humping",
 "h00r",
 "h0ar",
 "h0re",
 "hells",
 "hoar",
 "hoor",
 "hoore",
 "incest",
 "intercourse",
 "jack off",
 "jail bait",
 "jailbait",
 "jerk off",
 "jigaboo",
 "jiggaboo",
 "jiggerboo",
 "jizz",
 "juggs",
 "jackoff",
 "jap",
 "japs",
 "jerk-off",
 "jisim",
 "jiss",
 "jizm",
 "kike",
 "kinbaku",
 "kinkster",
 "kinky",
 "knobbing",
 "knob",
 "knobs",
 "knobz",
 "kunt",
 "kunts",
 "kuntz",
 "leather restraint",
 "leather straight jacket",
 "lemon party",
 "lolita",
 "lovemaking",
 "Lesbian",
 "Lezzian",
 "Lipshits",
 "Lipshitz",
 "make me come",
 "male squirting",
 "masturbate",
 "menage a trois",
 "milf",
 "missionary position",
 "motherfucker",
 "mound of venus",
 "mr hands",
 "muff diver",
 "muffdiving",
 "masochist",
 "masokist",
 "massterbait",
 "masstrbait",
 "masstrbate",
 "masterbaiter",
 "masterbate",
 "masterbates",
 "Motha Fucker",
 "Motha Fuker",
 "Motha Fukkah",
 "Motha Fukker",
 "Mother Fucker",
 "Mother Fukah",
 "Mother Fuker",
 "Mother Fukkah",
 "Mother Fukker",
 "mother-fucker",
 "Mutha Fucker",
 "Mutha Fukah",
 "Mutha Fuker",
 "Mutha Fukkah",
 "Mutha Fukker",
 "nambla",
 "nawashi",
 "negro",
 "neonazi",
 "nig nog",
 "nigga",
 "nigger",
 "nimphomania",
 "nipple",
 "nipples",
 "nsfw images",
 "nude",
 "nudity",
 "nympho",
 "nymphomania",
 "n1gr",
 "nastt",
 "nigger;",
 "nigur;",
 "niiger;",
 "niigr;",
 "octopussy",
 "omorashi",
 "one cup two girls",
 "one guy one jar",
 "orgasm",
 "orgy",
 "orafis",
 "orgasim;",
 "orgasm",
 "orgasum",
 "oriface",
 "orifice",
 "orifiss",
 "paedophile",
 "panties",
 "panty",
 "pedobear",
 "pedophile",
 "pegging",
 "penis",
 "phone sex",
 "piece of shit",
 "piss pig",
 "pissing",
 "pisspig",
 "playboy",
 "pleasure chest",
 "pole smoker",
 "ponyplay",
 "poof",
 "poop chute",
 "poopchute",
 "porn",
 "porno",
 "pornography",
 "prince albert piercing",
 "pthc",
 "pubes",
 "pussy",
 "packi",
 "packie",
 "packy",
 "paki",
 "pakie",
 "paky",
 "pecker",
 "peeenus",
 "peeenusss",
 "peenus",
 "peinus",
 "pen1s",
 "penas",
 "penis-breath",
 "penus",
 "penuus",
 "Phuc",
 "Phuck",
 "Phuk",
 "Phuker",
 "Phukker",
 "polac",
 "polack",
 "polak",
 "Poonani",
 "pr1c",
 "pr1ck",
 "pr1k",
 "pusse",
 "pussee",
 "puuke",
 "puuker",
 "queef",
 "queer",
 "queers",
 "queerz",
 "qweers",
 "qweerz",
 "qweir",
 "raghead",
 "raging boner",
 "rape",
 "raping",
 "rapist",
 "rectum",
 "reverse cowgirl",
 "rimjob",
 "rimming",
 "rosy palm",
 "rosy palm and her 5 sisters",
 "rusty trombone",
 "recktum",
 "retard",
 "s&m",
 "sadism",
 "scat",
 "schlong",
 "scissoring",
 "semen",
 "sex",
 "sexo",
 "sexy",
 "shaved beaver",
 "shaved pussy",
 "shemale",
 "shibari",
 "shit",
 "shota",
 "shrimping",
 "slanteye",
 "slut",
 "smut",
 "snatch",
 "snowballing",
 "sodomize",
 "sodomy",
 "spic",
 "spooge",
 "spread legs",
 "strap on",
 "strapon",
 "strappado",
 "strip club",
 "style doggy",
 "suck",
 "sucks",
 "suicide girls",
 "sultry women",
 "swastika",
 "swinger",
 "sadist",
 "scank",
 "schlong",
 "screwing",
 "semen",
 "sh1t",
 "sh1ter",
 "sh1ts",
 "sh1tter",
 "sh1tz",
 "shits",
 "shitter",
 "Shitty",
 "Shity",
 "shitz",
 "Shyt",
 "Shyte",
 "Shytty",
 "Shyty",
 "skanck",
 "skank",
 "skankee",
 "skankey",
 "skanks",
 "Skanky",
 "sluts",
 "Slutty",
 "slutz",
 "son-of-a-bitch",
 "tainted love",
 "taste my",
 "tea bagging",
 "threesome",
 "throating",
 "tied up",
 "tight white",
 "tit",
 "tits",
 "titties",
 "titty",
 "tongue in a",
 "topless",
 "tosser",
 "towelhead",
 "tranny",
 "tribadism",
 "tub girl",
 "tubgirl",
 "tushy",
 "twat",
 "twink",
 "twinkie",
 "two girls one cup",
 "turd",
 "undressing",
 "upskirt",
 "urethra play",
 "urophilia",
 "vagina",
 "venus mound",
 "vibrator",
 "violet wand",
 "vorarephilia",
 "voyeur",
 "vulva",
 "va1jina",
 "vag1na",
 "vagiina",
 "vaj1na",
 "vajina",
 "vullva",
 "wank",
 "wet dream",
 "wetback",
 "white power",
 "women rapping",
 "wrapping men",
 "wrinkled starfish",
 "w0p",
 "wh00r",
 "wh0re",
 "whore",
 "xrated",
 "xx",
 "xxx",
 "yaoi",
 "yellow showers",
 "yiffy",
 "zoophilia",
 "arschloch",
 "b17ch",
 "b1tch",
 "bi+ch",
 "boiolas",
 "buceta",
 "chink",
 "cipa",
 "clits",
 "dirsa",
 "ejakulate",
 "fatass",
 "fcuk",
 "fuk",
 "fux0r",
 "hoer",
 "hore",
 "jism",
 "kawk",
 "l3itch",
 "masterbat3",
 "motherfucker",
 "s.o.b.",
 "mofo",
 "nazi",
 "nutsack",
 "phuck",
 "pimpis",
 "scrotum",
 "shemale",
 "teets",
 "tits",
 "boobs",
 "b00bs",
 "teez",
 "testical",
 "testicle",
 "titt",
 "w00se",
 "whoar",
 "amcik",
 "andskota",
 "assrammer",
 "ayir",
 "bi7ch",
 "breasts",
 "butt-pirate",
 "cabron",
 "cazzo",
 "chraa",
 "chuj",
 "d4mn",
 "daygo",
 "dego",
 "dupa",
 "dziwka",
 "ejackulate",
 "Ekto",
 "enculer",
 "faen",
 "fanculo",
 "fanny",
 "feces",
 "feg",
 "Felcher",
 "ficken",
 "Flikker",
 "foreskin",
 "Fotze",
 "futkretzn",
 "gook",
 "guiena",
 "h0r",
 "h4x0r",
 "hell",
 "helvete",
 "honkey",
 "Huevon",
 "hui",
 "injun",
 "kike",
 "klootzak",
 "kraut",
 "knulle",
 "kuk",
 "kuksuger",
 "Kurac",
 "kurwa",
 "lesbo",
 "mamhoon",
 "mibun",
 "monkleigh",
 "mouliewop",
 "muie",
 "mulkku",
 "muschi",
 "nazis",
 "nepesaurio",
 "orospu",
 "perse",
 "picka",
 "pimmel",
 "pizda",
 "poontsee",
 "poop",
 "porn",
 "p0rn",
 "pr0n",
 "preteen",
 "pula",
 "pule",
 "puta",
 "puto",
 "qahbeh",
 "rautenberg",
 "schaffer",
 "schlampe",
 "schmuck",
 "screw",
 "sharmuta",
 "sharmute",
 "shipal",
 "shiz",
 "skribz",
 "skurwysyn",
 "sphencter",
 "spic",
 "spierdalaj",
 "splooge",
 "suka",
 "vittu",

		}; 
    @FXML
    private Button AddBtn;




    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Categorie> categoriePromotions = categeListdata.getPersons();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (Categorie categoriePromotion : categoriePromotions) {
            observableList.add(categoriePromotion.toString());
        }
        combo.setItems(observableList);
        addDynamicBadWordFilter(tf_adresse);
        addDynamicBadWordFilter(tf_description);
    }
    
    
    
     public String filterBadWords(String text) {
        for (String badWord : badWords) {
            if (text.toLowerCase().contains(badWord)) {
                String stars = "";
                for (int i = 0; i < badWord.length(); i++) {
                    stars += "*";
                }
                text = text.replaceAll(badWord, stars);
            }
        }
        return text;
    }
   public void addDynamicBadWordFilter(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String filteredText = filterBadWords(newValue);
            textField.setText(filteredText);
        });
    }
    

    @FXML
    private void ajouter(MouseEvent event) throws IOException {
        if (ValidateEmptyForm(tf_description, tf_adresse, picker_date)&&(ValidateDateDeb(picker_date))) {
            Categorie asso; // instance
            String nom = (String) combo.getValue(); // tekhou ell valeur mill combo box 
            CategorieService cdao = new CategorieService(); // instance service categorie 
            asso = cdao.getOneByName(nom); //
            java.sql.Date date_publication = java.sql.Date.valueOf(picker_date.getValue());
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(date_publication);
            String description = tf_description.getText();
            String adresse = tf_adresse.getText();
           // String lien = imageadd.get;

            Annonces p = new Annonces(adresse, description, imagep, formattedDate, asso);

            AnnonceService promotiondao = new AnnonceService();
            promotiondao.insert(p);
             FXMLLoader loader = new FXMLLoader(getClass().getResource("sample2.fxml"));
        
        Parent root = loader.load();
        Controller c = loader.getController();
        AddBtn.getScene().setRoot(root);
        }
        
    }

    private boolean ValidateEmptyForm(TextField description, TextField adresse, DatePicker d) {
        if (description.getText().equals("") || adresse.getText().equals("")
                || d.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();

            return false;
        } else {
            return true;
        }
    }
    
    private boolean ValidateDateDeb(DatePicker d){
         if (d.getValue().isAfter(LocalDate.now())) {
            return true;
        } else {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText(" Date début non valide");
             alert.showAndWait();
             
             return false;  
         }
     }
    
@FXML
    private void UploadImageActionPerformed(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog
        file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage image = ImageIO.read(file);
            WritableImage imagee = SwingFXUtils.toFXImage(image, null);
            imageadd.setImage(imagee);
            imageadd.setFitWidth(200);
            imageadd.setFitHeight(200);
            imageadd.scaleXProperty();
            imageadd.scaleYProperty();
            imageadd.setSmooth(true);
            imageadd.setCache(true);

            try {
                // save image to PNG file
                this.lien = UUID.randomUUID().toString();
                imagep = file.toURI().toURL().toString();
                File f = new File("src\\uploads\\" + this.lien + ".png");
                System.out.println(f.toURI().toString());
                ImageIO.write(image, "PNG", f);

            } catch (IOException ex) {
                Logger.getLogger(AjoutAnnonceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
    }

    @FXML
    private void front(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample2.fxml"));
        SampleController aec = loader.getController();
        Parent root = loader.load();
        frontBTN.getScene().setRoot(root);
        
    }

   

}
