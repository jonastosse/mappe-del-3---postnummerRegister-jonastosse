module Mappe.PostnummerRegister.jonastosse{
    requires javafx.controls;
    requires java.logging;
    exports no.ntnu.idatx2001.ui.view;
    exports no.ntnu.idatx2001.model;
    exports no.ntnu.idatx2001.ui.controller;
    exports no.ntnu.idatx2001.ui.factory;
    opens no.ntnu.idatx2001.ui.view to java.base;
    opens no.ntnu.idatx2001.model to java.base;
    opens no.ntnu.idatx2001.ui.controller to java.base;
    opens no.ntnu.idatx2001.ui.factory to java.base;
        }