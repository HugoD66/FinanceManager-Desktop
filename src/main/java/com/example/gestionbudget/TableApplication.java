package com.example.gestionbudget;

import com.example.gestionbudget.config.AppConfig;
import com.example.gestionbudget.db.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

//GIT REFERENCE : https://github.com/Jicay/FinanceTrackerDemo


public class TableApplication extends Application {

    private static final Logger logger = LoggerFactory.getLogger(TableApplication.class);
    private static String basePath;

    @Override
    public void start(Stage stage){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TableApplication.class.getResource("table-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1030, 550);
            stage.setTitle("EconoMe");
            stage.getIcons().add(new Image("./com/example/gestionbudget/assets/picture/budget-icon.png"));
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        logger.info("Démarrage de l'application...");

        basePath = AppConfig.getBasePathForOS();
        String applicationDir = basePath + File.separator + "EconoMe";
        File appDirectory = new File(applicationDir);

        if (!appDirectory.exists()) {
            appDirectory.mkdirs();
        }

        String dbPath = applicationDir + File.separator + "database.db";
        Database.setDbPath(dbPath);

        boolean dbInitialized = Database.isOK(dbPath);
        if (dbInitialized) {
            logger.info("La base de données a été correctement initialisée.");
        } else {
            logger.error("Échec de l'initialisation de la base de données.");
            return;
        }

        launch(args);
    }
}