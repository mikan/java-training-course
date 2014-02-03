package local.JPL.ch12.ex12_02;

public class Answers {

    // PassengerVehicle オブジェクトの定員を負の値に設定しようとした。
    public void answer1() {
        PassengerVehicle pv = new PassengerVehicle("test car", -1); // IllegalArgumentException
        pv.remove(); // return false -> 例外化？
    }

    // オブジェクトがその初期値を設定するのに使用する設定ファイルに、文法エラーが見つかった。
    public void answer2() {
        new Answers(); // コンストラクタで例外をスローする。
    }

    // プログラマが指定した単語を文字列の配列から検索するメソッドが、その単語を発見できない。
    public void answer3() {
        // null を返す、-1を返すとか
    }

    // openメソッドへ指定されたファイルが存在しない。
    public void answer4() {
        // FileNotFoundException とか
    }

    // openメソッドへ指定されたファイルは存在するが、セキュリティにより使用できない。
    public void answer5() {
        // SecurityException とか
    }

    // リモートのサーバプロセスにネットワークコネクションを確立しようとするが、リモートのマシンと接続できない。
    public void answer6() {
        // IOException とか
    }

    // リモートのサーバプロセスとのやり取りの最中に、ネットワークコネクションが切れる。
    public void answer7() {
        // これも IOException とか
    }

    public static void main(String[] args) {
    }
}
