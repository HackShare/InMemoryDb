package org.gawst.asyncdb;

import java.util.concurrent.CopyOnWriteArrayList;

import android.content.Context;

/**
 * a basic helper class to keep the content of a flat database in an {@link CopyOnWriteArrayList}
 * @author Steve Lhomme
 *
 * @param <E> the type of items stored in memory by the {@link InMemoryDbCopyOnWriteArrayList}
 */
public abstract class InMemoryDbCopyOnWriteArrayList<E> extends InMemoryDbList<E, CopyOnWriteArrayList<E>> {

	/**
	 * the array where the data are stored, locked when writing on it
	 */
	private CopyOnWriteArrayList<E> mData;

	/**
	 * @param db The already created {@link android.database.sqlite.SQLiteOpenHelper} to use as storage
	 * @param context Used to open or create the database
	 * @param name Database filename on disk
	 * @param logger The {@link Logger} to use for all logs (can be null for the default Android logs)
	 * @param initCookie Cookie to pass to {@link #preloadInit(Object, Logger)}
	 */
	protected InMemoryDbCopyOnWriteArrayList(DataSource<E> db, Context context, String name, Logger logger, Object initCookie) {
		super(db, context, name, logger, initCookie);
	}
	
	@Override
	protected void preloadInit(Object cookie, Logger logger) {
		super.preloadInit(cookie, logger);
		mData = new CopyOnWriteArrayList<E>();
	}
	
	@Override
	protected CopyOnWriteArrayList<E> getList() {
		return mData;
	}
}
