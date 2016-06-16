package controller;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
public class Controller implements IController 
{

	/** The view. */
	private IView	view;

	/** The model. */
	private IModel	model;

	/**
	 * Instantiates a new controller.
	 *
	 * @param view
	 *          the view
	 * @param model
	 *          the model
	 */
	public Controller(final IView view, final IModel model) 
	{
		this.setView(view);
		this.setModel(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see contract.IController#control()
	 */
	public void control() {
		//this.model.loadMessage();
		//this.view.printMessage("Appuyer sur les touches 'E', 'F', 'D' ou 'I', pour afficher Hello world dans la langue d votre choix.");
	}

	/**
	 * Sets the view.
	 *
	 * @param view
	 *          the new view
	 */
	private void setView(final IView view) {
		this.view = view;
	}

	/**
	 * Sets the model.
	 *
	 * @param model
	 *          the new model
	 */
	private void setModel(final IModel model) {
		this.model = model;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IController#orderPerform(contract.ControllerOrder)
	 */
	public void orderPerform(final ControllerOrder controllerOrder) 
	{
		switch (controllerOrder) 
		{
			case UP:
					this.getModel().MoveUP();break;
			case DOWN:
					this.getModel().MoveDW();break;
			case LEFT:
					this.getModel().MoveLF();break;
			case RIGHT:
					this.getModel().MoveRT();break;
			case UPPERRIGHT:
					this.getModel().MoveUpRt();break;
			case UPPERLEFT:
					this.getModel().MoveUpLf();break;
			case DOWNRIGHT:
					this.getModel().MoveDwRt();break;
			case DOWNLEFT:
					this.getModel().MoveDwLf();break;
			default: break;
		}
	}

	public IModel getModel() {
		return model;
	}

	
}
