
public class PieceWorker extends Employee{
	private double wagePerPiece;
	private int piecesProduced;
	public PieceWorker(String first,String last,String ssn,double WagePerPiece,int PiecesProduced)
	{
		super(first,last,ssn);
		setWagePerPiece(WagePerPiece);
		setPiecesProduced(PiecesProduced);
	}
	public void setWagePerPiece(double WagePerPiece)
	{
		if(wagePerPiece>=0.0)
			wagePerPiece = WagePerPiece;
		else
			throw new IllegalArgumentException("Wage per piece must be >=0.0");
	}
	public double getWagePerPiece()
	{
		return wagePerPiece;
	}
	public void setPiecesProduced(int PiecesProduced)
	{
		if(PiecesProduced>=0)
			piecesProduced = PiecesProduced;
		else
			throw new IllegalArgumentException("Pieces Produced must be>=0");
	}
	public int getPiecesProduced()
	{
		return piecesProduced;
	}
	@Override
	public double earnings()
	{
		return piecesProduced*wagePerPiece;
	}
	@Override
	public String toString()
	{
		return String.format("Piece Worker : %s\n%s: $%,.2f;%s: %d",super.toString(),"Wage per piece",getWagePerPiece(),"Pieces Produced",getPiecesProduced());
	}

}
