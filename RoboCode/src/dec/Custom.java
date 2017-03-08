package dec;
import robocode.*;
import robocode.robotinterfaces.IAdvancedRobot;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

public class Custom extends AdvancedRobot implements IAdvancedRobot {
    HashMap<String, Enemy> enemyList = new HashMap<String, Enemy>();

    @Override
    public void run() {
        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setAdjustRadarForRobotTurn(true);

        setColors(Color.black, Color.red, Color.white, Color.white, Color.white);
        //turnRadarRight(Double.POSITIVE_INFINITY);
        while (true) {
            manageMove();
            manageRadar();
            manageShoot();
            //calvinShoot();
            execute();
        }

    }

    public void manageMove() {
        double x = getBattleFieldWidth() / 2;
        double y = getBattleFieldHeight() / 2;
        double distance = 1000000;

        for (Enemy a : enemyList.values()) {
            if (a.distance < distance) {
                distance = a.distance;
                x = a.x;
                y = a.y;
            }
        }

        if (distance <= 200) {
            setTurnRight(1000);
            setAhead(1000);
            return;
        }

        /*
        if (distance<= 200 && distance >=190){
            setTurnRight(1000);
            setAhead(1000);
            return;
        }*/

        Point2D.Double enemyCenter = new Point2D.Double(x, y);
        //Point2D.Double nextPoint = new Point2D.Double(Math.cos(20*getTime())*200+x,Math.sin(20*getTime())*200+y);
        moveTo(enemyCenter);

    }

    public void moveTo(Point2D point) {
        double distance = Point2D.distance(getX(), getY(), point.getX(), point.getY());
        double angle = robocode.util.Utils.normalRelativeAngleDegrees(absoluteBearing(getX(), getY(), point.getX(), point.getY()) - getHeading());

        if (Math.abs(angle) > 90.0) {
            distance *= -1.0;
            if (angle > 0.0) {
                angle -= 180.0;
            } else {
                angle += 180.0;
            }
        }
        setTurnRight(angle);
        setAhead(distance);
    }

    public void manageRadar() {
        setTurnRadarRight(360);
    }

    public void calvinShoot() {

        Enemy target = new Enemy();
        enemyList.put(new Enemy().name, new Enemy());
        for (Enemy a : enemyList.values()) {
            if (target.distance > a.distance) {
                target = a;
            }
        }

        double absoluteTargetBearing = (getHeading() + target.bearing) % 360;
        double targetRelativeHeading = (target.heading + 360 - absoluteTargetBearing) % 360;
        double targetVelocityX = target.velocity * Math.sin(Math.toRadians(targetRelativeHeading));
        double velocityCoeffocient = target.distance / robocode.Rules.getBulletSpeed(3);
        double targetDistance = target.distance;
        double predictedAngleAddition = Math.toDegrees(Math.atan2(velocityCoeffocient * targetVelocityX, targetDistance));
        double predictiveTargetBearing = absoluteTargetBearing + predictedAngleAddition;
        double bearingFromGun = robocode.util.Utils.normalRelativeAngleDegrees(predictiveTargetBearing - getGunHeading());

        System.out.println("absoluteTargetBearing: " + absoluteTargetBearing + ", targetRelativeHeading: " + targetRelativeHeading + ", targetVelocityX: " + targetVelocityX + ", predictedAngleAddition: " + predictedAngleAddition);

        if (Math.abs(bearingFromGun) <= 3) {
            setTurnGunRight(bearingFromGun);
            if (getGunHeat() == 0) {
                //setFire(Math.min(1000/targetDistance, Rules.MAX_BULLET_POWER));
                setFire(3);
            }
        } else {
            setTurnGunRight(bearingFromGun);
        }
    }

    public void manageShoot() {

        Enemy e = new Enemy();
        enemyList.put(new Enemy().name, new Enemy());
        for (Enemy a : enemyList.values()) {
            if (e.distance > a.distance) {
                e = a;
            }
        }
        /*
        double abs = e.absBearing;
        double bulletPower = 3;
        double timeConstant = 1;
        double timeToHit = e.distance / robocode.Rules.getBulletSpeed(bulletPower) * timeConstant;
        double predictedDistance = timeToHit * e.velocity;
        double deltaX = predictedDistance * Math.sin(Math.toRadians(e.heading));
        double deltaY = predictedDistance * Math.cos(Math.toRadians(e.heading));// basic prediction

        if (e.previousEnemyValues.size()>0) { // advanced circular prediction
            //System.out.println("circular prediction activated");
            Enemy previousScan = e.previousEnemyValues.get(e.previousEnemyValues.size() - 1);
            double initialHeading = Math.toRadians(previousScan.heading);
            System.out.println(e.timeOfScan + " " + previousScan.timeOfScan);
            double deltaHeading = Math.toRadians(e.heading - initialHeading) / (e.timeOfScan - previousScan.timeOfScan);
            if (deltaHeading != 0) {
                System.out.println(deltaHeading);
                double relRadius = e.velocity / (deltaHeading);
                deltaX = (Math.cos(initialHeading) - Math.cos(initialHeading + deltaHeading)) * relRadius;
                deltaY = (Math.sin(initialHeading + deltaHeading) - Math.sin(initialHeading)) * relRadius;
            }
        }
        double x = e.x + deltaX;
        double y = e.y + deltaY;
        //System.out.println("X; " + x + " Y; " + y);


        //double theta = abs-getGunHeading();
        //setTurnGunRight(robocode.util.Utils.normalRelativeAngleDegrees(theta));
        setTurnGunRight(robocode.util.Utils.normalRelativeAngleDegrees(absoluteBearing(getX(), getY(), x, y) - getGunHeading()));
        if (e.distance < 500 && x > 0 && y > 0) {
            setFire(bulletPower);
        }
        */

        long time;
        long nextTime;
        double bulletPower = 3;
        double newX, newY;
        double diff = e.distance / robocode.Rules.getBulletSpeed(bulletPower) - e.timeOfScan;
        newY = e.y + Math.cos(Math.toRadians(e.heading)) * e.velocity * diff;
        newX = e.x + Math.sin(Math.toRadians(e.heading)) * e.velocity * diff;
        Point2D.Double p;
        p = new Point2D.Double(e.x, e.y);
        for (int i = 0; i < 10; i++){
            nextTime = (Math.round((Point2D.distance(getX(),getY(),p.x,p.y)/(20-(3*bulletPower)))));
            time = getTime() + nextTime;



            double changehead = 0;
            try {
                Enemy previousScan = e.previousEnemyValues.get(e.previousEnemyValues.size() - 1);
                changehead = e.heading - previousScan.heading;
            }catch (Exception e1){
                changehead = 0;
            }
            /**if there is a significant change in heading, use circular
             path prediction**/
            if (Math.abs(changehead) > 0.00001) {
                double radius = e.velocity/changehead;
                double tothead = diff * changehead;
                newY = e.y + (Math.sin(Math.toRadians(e.heading) + tothead) * radius) -
                        (Math.sin(Math.toRadians(e.heading)) * radius);
                newX = e.x + (Math.cos(Math.toRadians(e.heading)) * radius) -
                        (Math.cos(Math.toRadians(e.heading) + tothead) * radius);
            }
            /**if the change in heading is insignificant, use linear
             path prediction**/
            else {
                newY = e.y + Math.cos(Math.toRadians(e.heading)) * e.velocity * diff;
                newX = e.x + Math.sin(Math.toRadians(e.heading)) * e.velocity * diff;
            }




            p = new Point2D.Double(newX,newY);
        }
        /**Turn the gun to the correct angle**/
        double gunOffset = getGunHeadingRadians() -
                (Math.PI/2 - Math.atan2(p.y - getY(), p.x - getX()));
        setTurnGunLeftRadians(robocode.util.Utils.normalRelativeAngle(gunOffset));
        if (newX>0&&newY>0){
            setFire(bulletPower);
        }
    }

    public Enemy getClosestRobot() {
        Enemy closestRobot = new Enemy();
        enemyList.put(new Enemy().name, new Enemy());
        if (enemyList.isEmpty()) {
            return new Enemy();
        }
        for (Enemy a : enemyList.values()) {
            if (closestRobot.distance > a.distance) {
                closestRobot = a;
            }
        }
        return closestRobot;
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent enemy) {

        if (enemyList.containsKey(enemy.getName())) {
            enemyList.get(enemy.getName()).update(enemy);

            //enemyList.remove(enemy.getName());
            //enemyList.put(enemy.getName(), new Enemy(enemy));
        } else {
            enemyList.putIfAbsent(enemy.getName(), new Enemy(enemy));
        }
    }


    public class Enemy {
        double  x, y, velocity, distance, bearing, heading, energy, timeOfScan, absBearing;
        String name;
        ArrayList<Enemy> previousEnemyValues = new ArrayList<Enemy>();

        Enemy() {
            this.distance = 80000;
            this.absBearing = 0;
            this.name = "StarterRobot";
        }

        Enemy(ScannedRobotEvent enemy) {
            this.timeOfScan = enemy.getTime();
            this.name = enemy.getName();
            this.velocity = enemy.getVelocity();
            this.distance = enemy.getDistance();
            this.bearing = enemy.getBearing();
            this.heading = enemy.getHeading();
            this.energy = enemy.getEnergy();
            this.absBearing = bearing + getHeading();
            double angle = Math.toRadians((getHeading() + bearing % 360));
            this.x = (getX() + Math.sin(angle) * distance);
            this.y = (getY() + Math.cos(angle) * distance);
            System.out.println("New Enemy " + name);

        }
        Enemy(Enemy enemy){
            this.timeOfScan = enemy.timeOfScan;
            this.name = enemy.name;
            this.velocity = enemy.velocity;
            this.distance = enemy.distance;
            this.bearing  = enemy.bearing;
            this.heading = enemy.heading;
            this.energy = enemy.energy;
            this.absBearing= enemy.absBearing;
            this.x = enemy.x;
            this.y= enemy.y;

        }

        void update(ScannedRobotEvent enemy) {
            previousEnemyValues.add(new Enemy(this));
            this.timeOfScan = enemy.getTime();
            this.name = enemy.getName();
            this.velocity = enemy.getVelocity();
            this.distance = enemy.getDistance();
            this.bearing = enemy.getBearing();
            this.heading = enemy.getHeading();
            this.energy = enemy.getEnergy();
            this.absBearing = bearing + getHeading();
            double angle = Math.toRadians((getHeading() + bearing % 360));
            this.x = (getX() + Math.sin(angle) * distance);
            this.y = (getY() + Math.cos(angle) * distance);
            System.out.println("updated " + name);

        }

    }



    /*
        @Override
        public void onBulletHit(BulletHitEvent event) {
        }

        @Override
        public void onBulletHitBullet(BulletHitBulletEvent event) {
        }

        @Override
        public void onBulletMissed(BulletMissedEvent event) {
        }

        @Override
        public void onHitByBullet(HitByBulletEvent event) {
        }

        @Override
        public void onHitRobot(HitRobotEvent event) {
        }

        @Override
        public void onHitWall(HitWallEvent event) {
            setTurnRight(90);
        }*/

    @Override
    public void onRobotDeath(RobotDeathEvent event) {
        enemyList.remove(event.getName());
    }

    /*

    @Override
    public void onWin(WinEvent event) {
    }

    @Override
    public void onRoundEnded(RoundEndedEvent event) {
    }

    @Override
    public void onBattleEnded(BattleEndedEvent event) {
    }
    */
    double absoluteBearing(double x1, double y1, double x2, double y2) {
        double xo = x2 - x1;
        double yo = y2 - y1;
        double hyp = Point2D.distance(x1, y1, x2, y2);
        double arcSin = Math.toDegrees(Math.asin(xo / hyp));
        double bearing = 0;

        if (xo > 0 && yo > 0) { // both pos: lower-Left
            bearing = arcSin;
        } else if (xo < 0 && yo > 0) { // x neg, y pos: lower-right
            bearing = 360 + arcSin; // arcsin is negative here, actuall 360 - ang
        } else if (xo > 0 && yo < 0) { // x pos, y neg: upper-left
            bearing = 180 - arcSin;
        } else if (xo < 0 && yo < 0) { // both neg: upper-right
            bearing = 180 - arcSin; // arcsin is negative here, actually 180 + ang
        }

        return bearing;
    }
}
