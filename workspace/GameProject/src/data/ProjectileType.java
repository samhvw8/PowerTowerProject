package data;

import static helpers.Artist.quickLoad;

import org.newdawn.slick.opengl.Texture;

public enum ProjectileType {

    CannonBall(quickLoad("bullet"), 10, 500),
    IceBall(quickLoad("bullet"), 10, 500);

    Texture texture;
    int damage, range;
    float speed;

    ProjectileType(Texture texture, int damage, float speed) {
        this.texture = texture;
        this.damage = damage;
        this.speed = speed;
    }

}
