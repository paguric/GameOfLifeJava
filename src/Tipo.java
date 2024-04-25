public enum Tipo {
    STILL_LIFE, OSCILLATOR, SPACESHIP, GUN;
    @Override
    public String toString() {
        String name = name().toLowerCase().replace('_', ' ');
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }


}
