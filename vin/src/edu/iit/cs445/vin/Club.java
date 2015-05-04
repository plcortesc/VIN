package edu.iit.cs445.vin;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;

public class Club implements java.io.Serializable {
	
	private static final long serialVersionUID = -4601574179158106799L;

	public Club() {}


	public ArrayList<Receipt> receipt(){
		return this.receipt();
	}
	
	public AddSubsResponse addSubscriber(ArrayList<Subscriber> subs, String st, String c, String s, String z, String n, String e, String p, String tw, String fb){
		AddSubscriber sr = new AddSubscriber(st,c,s,z,n,e,p,tw,fb);
		AddSubsResponse ssd = sr.addAccount(subs,null);
		
		return ssd;
	}
	
	public AddSubsResponse setSubscriber(int UID, String name, String email, String phone, String tw, String fb,ArrayList<Subscriber> subs){
		AddSubsResponse ssd = null;
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				AddSubscriber sr = new AddSubscriber(subscriber.getAddress().getStreet(),subscriber.getAddress().getCity(),
								   subscriber.getAddress().getState(),subscriber.getAddress().getZip(),name,email,phone,tw,fb);
				subs.remove(subscriber);
				ssd = sr.addAccount(subs,UID);
				return ssd;	
			}
		}return ssd;
	}
	
	public SubscriberResponse printSubscriber(int UID,ArrayList<Subscriber> subs){
		SubscriberResponse sr = null;
		for(Subscriber s: subs){
			if(s.getID()==UID){
				sr = new SubscriberResponse(s.getEmail(),s.getName(),s.getPhone(),s.getAddress(),s.getFacebook(),s.getTwitter());
			}
		}
		return sr;
	}
	
	public SearchResponse search(int UID, String kw,ArrayList<Subscriber> subs){
		SearchResponse sr = null;
		for(Subscriber s: subs){
			if(s.getID()==UID){
				sr = new SearchResponse(s.searchWine(kw),s.searchShipment(kw),s.searchNote(kw));
			}
		}
		return sr;
	}
	
	public GetShipmentsResponse printSubShipment(int UID,ArrayList<Subscriber> subs){
		ArrayList<ShipmentResponse> sr = new ArrayList<ShipmentResponse>();
		for(Subscriber s: subs){
			if(s.getID()==UID){
				for(Shipment ship: s.getShipments()){
					sr.add(new ShipmentResponse(ship.getID(),ship.getYm(),ship.getStatus()));
				}
			}
		}
		return new GetShipmentsResponse(sr);
	}
	
	public ShipmentDetailResponse printSubShipSID(int UID, int SID,ArrayList<Subscriber> subs){
		ShipmentDetailResponse sdr = null;
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Shipment shipment: subscriber.getShipments()){
					if(shipment.getID()==SID){
						String date = Integer.toString(shipment.getDate().get(Calendar.DAY_OF_MONTH))+
									  "/"+Integer.toString(shipment.getDate().get(Calendar.MONTH))+
									  "/"+Integer.toString(shipment.getDate().get(Calendar.YEAR));
						ArrayList<WineResponse> wrs = new ArrayList<WineResponse>();
						for(Wine w:shipment.getMsWines()){
							wrs.add(new WineResponse(w.getID(),w.getLabelName()));
						}
						sdr = new ShipmentDetailResponse(shipment.getYm(),shipment.getStatus(), date, shipment.getMst(), wrs);
						
					}
				}
			}
		}return sdr;			
	}
	
	public ShipNotesResponse printShipNote(int UID, int SID,ArrayList<Subscriber> subs){
		ArrayList<NoteResponse> snr = new ArrayList<NoteResponse>();
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Shipment shipment: subscriber.getShipments()){
					if(shipment.getID()==SID){
						for(Note n: shipment.getNotes()){
							String date = Integer.toString(n.getDate().get(Calendar.DAY_OF_MONTH))+
									  "/"+Integer.toString(n.getDate().get(Calendar.MONTH))+
									  "/"+Integer.toString(n.getDate().get(Calendar.YEAR));
							snr.add(new NoteResponse(n.getID(),date,n.getContent()));
						}
						
					}
				}
			}
		}return new ShipNotesResponse(snr);
	}
	
	public PostNoteResponse postShipmentNote(int UID, int SID, Note n,ArrayList<Subscriber> subs){
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Shipment shipment: subscriber.getShipments()){
					if(shipment.getID()==SID){
						shipment.addNote(n);
					}
				}
			}
		}return new PostNoteResponse(n.getID());
	}
	
	public NoteIDResponse printShipNoteNID(int UID, int SID, int NID,ArrayList<Subscriber> subs){
		NoteIDResponse nr = null;
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Shipment shipment: subscriber.getShipments()){
					if(shipment.getID()==SID){
						for(Note note: shipment.getNotes()){
							if(note.getID()==NID){
								String date = Integer.toString(note.getDate().get(Calendar.MONTH))+
										  "/"+Integer.toString(note.getDate().get(Calendar.DAY_OF_MONTH))+
										  "/"+Integer.toString(note.getDate().get(Calendar.YEAR));
								nr = new NoteIDResponse(date,note.getContent());
							}
						}
					}
				}
			}
		}return nr;
	}
	
	public void setShipNoteNID(int UID, int SID, int NID, Note n,ArrayList<Subscriber> subs){
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Shipment shipment: subscriber.getShipments()){
					if(shipment.getID()==SID){
						for(Note note :shipment.getNotes()){
							if(note.getID()==NID){
								shipment.getNotes().remove(note);
								shipment.addNote(n);
							}
						}
					}
				}
			}
		}
	}
	
	public void removeShipNoteNID(int UID, int SID, int NID,ArrayList<Subscriber> subs){
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Shipment shipment: subscriber.getShipments()){
					if(shipment.getID()==SID){
						shipment.removeNote(NID);
					}
				}
			}
		}
	}
		
	public GetWinesResponse printWines(int UID,ArrayList<Subscriber> subs){
		ArrayList<WineResponse> wrs = new ArrayList<WineResponse>();
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Wine w: subscriber.getWines()){
					wrs.add(new WineResponse(w.getID(),w.getLabelName()));
				}	
			}
		}return new GetWinesResponse(wrs);
	}
	
	public WineResponse printWinesWID(int UID, int WID,ArrayList<Subscriber> subs){
		WineResponse wr = null;
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Wine w: subscriber.getWines()){
					if(w.getID()==WID){
						wr = new WineResponse(w.getID(),w.getLabelName());
					}
				}				
			}
		}return wr;
	}
	
	public GetWineNotesResponse printWineNotes(int UID, int WID,ArrayList<Subscriber> subs){
		ArrayList<NoteResponseSearch> nrs = new ArrayList<NoteResponseSearch>();
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Wine w: subscriber.getWines()){
					if(w.getID()==WID){
						for(Note n: w.getNotes()){
							nrs.add(new NoteResponseSearch(n.getID(),n.getContent()));
						}
					}
				}
			}
		}return new GetWineNotesResponse(nrs);
	}
	
	public PostNoteResponse postWinesNote(int UID, int WID, Note n,ArrayList<Subscriber> subs){
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Wine w: subscriber.getWines()){
					if(w.getID()==WID){
						w.addNote(n);
					}
				}
			}
		}return new PostNoteResponse(n.getID());
	}
	
	public NoteResponseSearch printWineNoteNID(int UID, int WID, int NID,ArrayList<Subscriber> subs){
		NoteResponseSearch nrs = null;
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Wine w: subscriber.getWines()){
					if(w.getID()==WID){
						for(Note note: w.getNotes()){
							if(note.getID()==NID){
								nrs = new NoteResponseSearch(note.getID(),note.getContent());
							}
						}
					}
				}
			}
		}return nrs;
	}
	
	public void setWineNoteNID(int UID, int WID, int NID, Note n,ArrayList<Subscriber> subs){
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Wine w: subscriber.getWines()){
					if(w.getID()==WID){
						w.updateNote(NID, n);
					}
				}
			}
		}
	}
	
	public void removeWineNoteNID(int UID, int WID, int NID,ArrayList<Subscriber> subs){
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Wine w: subscriber.getWines()){
					if(w.getID()==WID){
						w.removeNote(NID);
					}
				}
			}
		}
	}
	
	public WineRating printWineRating(int UID, int WID,ArrayList<Subscriber> subs){
		WineRating wr = null;
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Wine w: subscriber.getWines()){
					if(w.getID()==WID){
						wr = new WineRating(w.getRating());
					}
				}
			}
		}return wr;
	}
	
	public void postWineRating(int UID, int WID, int r,ArrayList<Subscriber> subs){
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Wine w: subscriber.getWines()){
					if(w.getID()==WID){
						w.addRating(r);;
					}
				}
			}
		}
	}
	
	public PostNoteResponse addAdmin(String name,ArrayList<Admin> admin){
		Admin ad = new Admin(name);
		admin.add(ad);
		return new PostNoteResponse(ad.getID());
	}
	
	public GetAdminResponse printAdmin(ArrayList<Admin> admin){
		ArrayList<AdminResponse> ar = new ArrayList<AdminResponse>();
		for(Admin a: admin){
			ar.add(new AdminResponse(a.getID(),a.getName()));
		}return new GetAdminResponse(ar);
	}
	
	public void setAdmin(String name, int AID,ArrayList<Admin> admin){
		for(Admin a: admin){
			if(a.getID()==AID){
				Admin adm = new Admin(name);
				adm.setDate(a.getDate());
				adm.setID(a.getID());
				admin.remove(a);
			}
		}
	}
	
	public AdminDetailResponse printAdminAID(int AID,ArrayList<Admin> admin){
		AdminDetailResponse adr = null;
		for(int i=0;i<admin.size();i++){
			if(admin.get(i).getID()==AID){
				String str = admin.get(i).getDate().get(Calendar.MONTH)+"/"+
						admin.get(i).getDate().get(Calendar.DAY_OF_MONTH)+"/"+
						admin.get(i).getDate().get(Calendar.YEAR)+"\n";
				int created_by;
				if(i>0){created_by = admin.get(i-1).getID();}
				else{ created_by = admin.get(i).getID();}
				adr = new AdminDetailResponse(admin.get(i).getID(), admin.get(i).getName(), str, created_by);
			}
		}return adr;	
	}
	
	public RevenueResponse adminRevenue(ArrayList<Subscriber> subs){
		int units_delivered=0;
		int wine_revenue=0;
		int delivery_revenue=0;
		
		for(Subscriber s: subs){
			for(Shipment ship: s.getShipments()){
				if(ship.getStatus()==ShipmentStatus.DELIVERED){
					units_delivered += ship.getSize() * 6;
					wine_revenue += ship.getSize()*ship.getMsDeliveryfee();
					delivery_revenue += ship.getDeliveryfee();
				}
			}

		}
		return new RevenueResponse(units_delivered, wine_revenue, delivery_revenue);
	}
		
	public RevenueResponse adminRevenue(YearMonth startDate, YearMonth endDate,ArrayList<Subscriber> subs){
		int units_delivered=0;
		int wine_revenue=0;
		int delivery_revenue=0;
		
		for(Subscriber s: subs){
			for(Shipment ship: s.getShipments()){
				if(ship.getStatus()==ShipmentStatus.DELIVERED){
					if(ship.getYm().isAfter(startDate)&&ship.getYm().isBefore(endDate)){
						units_delivered += ship.getSize() * 6;
						wine_revenue += ship.getSize()*ship.getMsDeliveryfee();
						delivery_revenue += ship.getDeliveryfee();
					}
				}
			}
		}
		return new RevenueResponse(units_delivered, wine_revenue, delivery_revenue);
	}
	
	public void postMs(String mst, String ym, ArrayList<Wine> wines,ArrayList<MonthlySelection> monthlySelection,ArrayList<Admin> admin) {
		admin.get(0).createMs(mst, ym, wines,monthlySelection );
	}
	
	public MonthlySelectionResponse printMs(ArrayList<MonthlySelection> monthlySelection){
		ArrayList<MonthlyResponse> mr = new ArrayList<MonthlyResponse>();
		for(MonthlySelection ms: monthlySelection){
			mr.add(new MonthlyResponse(ms.getID(),ms.getYm(),ms.getMst()));
		}
		return new MonthlySelectionResponse(mr);
	}

	public MstDetailResponse printMsMID(int MID,ArrayList<MonthlySelection> monthlySelection){
		MstDetailResponse mdr = null;
		ArrayList<WineResponse> wr = new ArrayList<WineResponse>();
		for(MonthlySelection ms: monthlySelection){
			if(ms.getID()==MID){
				String date = Integer.toString(ms.getDate().get(Calendar.MONTH))+"/"+
						Integer.toString(ms.getDate().get(Calendar.DAY_OF_MONTH))+"/"+
						Integer.toString(ms.getDate().get(Calendar.YEAR));
				for(Wine w: ms.getMs()){
					wr.add(new WineResponse(w.getID(),w.getLabelName()));
				}
				mdr = new MstDetailResponse(MID,ms.getMst(),ms.getYm(),date,ms.getAID(),wr);
			}
		}return mdr;
	}
		
	public ToDeliverResponse shipmentsToDeliver(ArrayList<Subscriber> subs){
		YearMonth ym = YearMonth.now();
		ArrayList<DeliverTo> dt = new ArrayList<DeliverTo>();
		ArrayList<Subscriber> subsToDeliver = new ArrayList<Subscriber>();
		for(Subscriber s:subs){
			for(Shipment ship: s.getShipments()){
				if(ship.getStatus()==ShipmentStatus.PENDING &&(ship.getYm() == ym)){
					subsToDeliver.add(s);
					dt.add(new DeliverTo(s.getName(),s.getPhone(),s.getEmail(),s.getAddress(),s.getMst()));
				}
			}
		}
		return new ToDeliverResponse(dt);
	}
	
	public PostNoteResponse postReceipt(int SID, String nameReceiver, int age, ArrayList<Receipt> receipts,ArrayList<Subscriber> subs){
		Receipt r = null;
		for(Subscriber s: subs){
			for(Shipment ship: s.getShipments()){
				if(ship.getStatus()==ShipmentStatus.PENDING && ship.getID()==SID){
					ship.setStatus(ShipmentStatus.DELIVERED);
					if(age>21){
						r = new Receipt(s,nameReceiver);
						receipts.add(r);
					}
				}
			}
		}return new PostNoteResponse(r.getID());
	}
	
	public GetReceiptsResponse printReceipts(ArrayList<Receipt> receipts){
		ArrayList<ReceiptResponse> rr = new ArrayList<ReceiptResponse>();
		for(Receipt r: receipts){
			String date = Integer.toString(r.getDate().get(Calendar.DAY_OF_MONTH))+"-"
					 +Integer.toString(r.getDate().get(Calendar.MONTH))+"-"+Integer.toString(r.getDate().get(Calendar.YEAR));
			rr.add(new ReceiptResponse(r.getID(),date, r.getSID(),r.getSName()));
		}return new GetReceiptsResponse(rr);
	}
	
	public ReceiptDetailResponse printReceiptRID(int RID, ArrayList<Receipt> receipts){
		ReceiptDetailResponse rdr = null;
		for(Receipt r: receipts){
			if(r.getID()==RID){
				String time = Integer.toString(r.getDate().get(Calendar.HOUR))+":"
						 +Integer.toString(r.getDate().get(Calendar.MINUTE));
				String date = Integer.toString(r.getDate().get(Calendar.DAY_OF_MONTH))+"-"
						 +Integer.toString(r.getDate().get(Calendar.MONTH))+"-"+Integer.toString(r.getDate().get(Calendar.YEAR));
				rdr = new ReceiptDetailResponse(r.getID(),time, date, r.getSID(),r.getSName(),r.getReceiver());
			}
		}return rdr;
	}
	
	public WineDetailResponse printAllWinesWID(int WID, ArrayList<Subscriber> subs){
		for(Subscriber subscriber: subs){
			for(Wine w: subscriber.getWines()){
				if(w.getID()==WID){
					return new WineDetailResponse(WID, w.getVariety(),w.getType(), w.getLabelName(),w.getGrape(),w.getRegion(), w.getCountry(),w.getMaker(),w.getYear(),w.getNumberOfRatings(), w.getRating());
				}
			}
		}return null;
	}
}
