package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static double closestPair(Point[] points, Metrics metrics) {
        Point[] pointsByX = points.clone();
        Arrays.sort(pointsByX, Comparator.comparingDouble(p -> p.x));

        Point[] pointsByY = points.clone();
        Arrays.sort(pointsByY, Comparator.comparingDouble(p -> p.y));

        return closest(pointsByX, pointsByY, 0, points.length - 1, metrics);
    }

    private static double closest(Point[] pointsByX, Point[] pointsByY, int left, int right, Metrics metrics) {
        if (right - left <= 3) {
            return bruteForce(pointsByX, left, right, metrics);
        }

        int mid = (left + right) / 2;
        Point midPoint = pointsByX[mid];

        Point[] leftY = Arrays.stream(pointsByY).filter(p -> p.x <= midPoint.x).toArray(Point[]::new);
        Point[] rightY = Arrays.stream(pointsByY).filter(p -> p.x > midPoint.x).toArray(Point[]::new);

        double dLeft = closest(pointsByX, leftY, left, mid, metrics);
        double dRight = closest(pointsByX, rightY, mid + 1, right, metrics);

        double d = Math.min(dLeft, dRight);

        // Полоса (strip)
        Point[] strip = Arrays.stream(pointsByY)
                .filter(p -> Math.abs(p.x - midPoint.x) < d)
                .toArray(Point[]::new);

        return Math.min(d, stripClosest(strip, d, metrics));
    }

    private static double bruteForce(Point[] points, int left, int right, Metrics metrics) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                metrics.incrementComparisons();
                double dist = distance(points[i], points[j]);
                if (dist < min) min = dist;
            }
        }
        return min;
    }

    private static double stripClosest(Point[] strip, double d, Metrics metrics) {
        double min = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
                metrics.incrementComparisons();
                double dist = distance(strip[i], strip[j]);
                if (dist < min) min = dist;
            }
        }
        return min;
    }

    private static double distance(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static class Point {
        public final double x, y;
        public Point(double x, double y) {
            this.x = x; this.y = y;
        }
    }
}
