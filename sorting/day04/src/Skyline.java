import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Skyline {

    static class Point {
        int x, y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Building {
        private int l, r, h;
        Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    // Given an array of buildings, return a list of points representing the skyline
    public static List<Point> skyline(Building[] B) {
        // Base Case
        ArrayList<Point> points = new ArrayList<>();
        if(B.length < 1) {
            return new ArrayList<>();
        }
        else if(B.length == 1) {
            Point building = new Point(B[0].l, B[0].h);
            Point ground = new Point(B[0].r, 0);
            points.add(building);
            points.add(ground);
            return points;
        }
        int m = B.length / 2;

        Building[] tempLeft = new Building[m];
        Building[] tempRight = new Building[B.length - m];

        System.arraycopy(B, 0, tempLeft, 0, m);
        System.arraycopy(B, m, tempRight, 0, B.length - m);

        ArrayList<Point> leftLine = (ArrayList<Point>)skyline(tempLeft);
        ArrayList<Point> rightLine = (ArrayList<Point>)skyline(tempRight);

        return combine(leftLine, rightLine);
    }

    private static List<Point> combine (ArrayList<Point>ll, ArrayList<Point>rl){
        // Starting from the closest points, work your way back out
        ArrayList<Point> res = new ArrayList<>();
        int li;
        int ri = 0;
        for(li=0; li < ll.size() && ll.get(li).x < rl.get(ri).x; li++) {
            res.add(ll.get(li));
        }
        if(res.size() < 1 && ll.get(li).x == rl.get(ri).x) {
            if(ll.get(li).y > rl.get(ri).y) {
                res.add(ll.get(li));
                li++;
            }
            else {
                res.add(rl.get(ri));
                ri++;
            }
        }
        while(li < ll.size() && ri < rl.size()) {
            Point right = rl.get(ri);
            Point left = ll.get(li);
            Point current_b = res.get(res.size()-1);

            if(right.x==left.x){
                int next_y = Math.max(right.y, left.y);
                if(next_y != current_b.y) {
                    res.add(new Point(right.x, Math.max(right.y, left.y)));
                }
                ri++;li++;
                continue;
            }

            int current_x = Math.min(right.x, left.x);
            if(current_x == right.x) {
                if(right.y > current_b.y) {
                    res.add(right);
                }
                else if(right.y < current_b.y && ll.get(li-1).y != current_b.y) {
                    res.add(new Point(right.x, ll.get(li-1).y));
                }
                ri++;
            }
            else if(current_x == left.x) {
                if(left.y > current_b.y) {
                    res.add(left);
                }
                else if(left.y < current_b.y && rl.get(ri-1).y != current_b.y) {
                    res.add(new Point(left.x, rl.get(ri-1).y));
                }
                li++;
            }
        }
        for(int i = li; i < ll.size(); i++) res.add(ll.get(i));
        for(int i = ri; i < rl.size(); i++) res.add(rl.get(i));
        return res;
    }
}
