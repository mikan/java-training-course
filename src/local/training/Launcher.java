package local.training;

/** 練習問題起動切換えクラスです。 */
public class Launcher {

	/** main メソッド */
	public static void main(String[] args) {
		// 練習問題を選択
		Object target = new local.training.jpl.chapter1.Problem1();
		
		System.out.println("[Launcher] 実行対象: " + target.getClass().getName());
		
		// ExecMethod インタフェース確認
		if (target instanceof ExecMethod) {
			((ExecMethod)target).exec();
		} else {
			System.err.println("ExecMethod インタフェースを使用してください。");
		}
	}

}
