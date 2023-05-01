package don;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.MapRectangleImpl;

public class MapExample {
    public static void main(String[] args) {
        // Create a new JMapViewer instance
        JMapViewer mapViewer = new JMapViewer();

        // Set the default zoom level and center point of the map
        mapViewer.setDisplayPosition(new Coordinate(48.858093, 2.294694), 13);

        // Add a marker to the map
        MapMarkerDot marker = new MapMarkerDot(new Coordinate(48.858093, 2.294694));
        mapViewer.addMapMarker(marker);
mapViewer.zoomIn();
        // Add a rectangle to the map

        // Add a polygon to the map
        

        // Create a new JFrame to display the map
        JFrame frame = new JFrame("JMapViewer Example");
        frame.setLayout(new BorderLayout());
        frame.add(mapViewer, BorderLayout.CENTER);
        frame.setSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
