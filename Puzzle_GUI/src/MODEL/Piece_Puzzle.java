package MODEL;

public class Piece_Puzzle  {

    private int id;

    public Piece_Puzzle( int id  ) {

        this.id = id;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece_Puzzle that = (Piece_Puzzle) o;
        return getId() == that.getId();
    }


    public int getId() {
        return id;
    }


}
