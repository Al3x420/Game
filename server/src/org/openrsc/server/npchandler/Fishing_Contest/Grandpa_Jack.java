/**
* Generated By NPCScript :: A scripting engine created for openrsc by Zilent
*/

//scripted by Mr. Zain

package org.openrsc.server.npchandler.Fishing_Contest;
import org.openrsc.server.event.DelayedQuestChat;
import org.openrsc.server.event.SingleEvent;
import org.openrsc.server.model.ChatMessage;
import org.openrsc.server.model.MenuHandler;
import org.openrsc.server.model.Npc;
import org.openrsc.server.model.Player;
import org.openrsc.server.model.Quest;
import org.openrsc.server.model.World;
import org.openrsc.server.npchandler.NpcHandler;



public class Grandpa_Jack implements NpcHandler {

	public void handleNpc(final Npc npc, final Player owner) throws Exception {
		npc.blockedBy(owner);
		owner.setBusy(true);
		Quest q = owner.getQuest(26);
		if(q != null) {
			if(q.finished()) {
				finished(npc, owner);
			} else {
				switch(q.getStage()) {
					case 0:
						noQuestStarted(npc, owner);
						break;
					case 1:
						questStage1(npc, owner);
						break;
					case 2:
						questStage1(npc, owner);
						break;
					case 3:
						questStage1(npc, owner);
						break;
					case 4:
						finished(npc, owner);
						break;
				}
			}
		} else {
			noQuestStarted(npc, owner);
		}
	}
	
	private void questStage1(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Hello young man", "Come to visit old Grandpa Jack?", "I can tell ye stories for sure", "I used to be the best fisherman these parts have seen"}, true) {
			public void finished() {
				World.getDelayedEventHandler().add(new SingleEvent(owner, 1500) {
					public void action() {
						final String[] options107 = {"Are you entering the fishing competition?", "Sorry I don't have time now"};
						owner.setBusy(false);
						owner.sendMenu(options107);
						owner.setMenuHandler(new MenuHandler(options107) {
							public void handleReply(final int option, final String reply) {
								owner.setBusy(true);
								for(Player informee : owner.getViewArea().getPlayersInView()) {
									informee.informOfChatMessage(new ChatMessage(owner, reply, npc));
								}
								switch(option) {
									case 0:
										fishingInfo(npc, owner);
										break;
									case 1:
										owner.setBusy(false);
										npc.unblock();
										break;
								}
							}
						});
					}
				});
			}
		});
	}
	
	private void fishingInfo(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Ah the Hemenster fishing competition", "I know all about that", "I won that four years straight", "I'm too old for that lark now though"}) {
			public void finished() {
				World.getDelayedEventHandler().add(new SingleEvent(owner, 1500) {
					public void action() {
						final String[] options107 = {"I don't suppose you could give me any hints?", "That's less competition for me then"};
						owner.setBusy(false);
						owner.sendMenu(options107);
						owner.setMenuHandler(new MenuHandler(options107) {
							public void handleReply(final int option, final String reply) {
								owner.setBusy(true);
								for(Player informee : owner.getViewArea().getPlayersInView()) {
									informee.informOfChatMessage(new ChatMessage(owner, reply, npc));
								}
								switch(option) {
									case 0:
										fishingHints(npc, owner);
										break;
									case 1:
										owner.setBusy(false);
										npc.unblock();
										break;
								}
							}
						});
					}
				});
			}
		});
	}
	
	private void fishingHints(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Well you sometimes get these really big fish", "In the water just by the outflow pipes", "Think they're some kind of carp", "Try to get a spot around there", "The best sort of bait for them is red vine worms", "I used to get those from Mcgruber's wood, north of here", "Dig around with a spade in the red vines up there"}) {
			public void finished() {
			owner.setBusy(false);
			npc.unblock();
			}
		});
	}
	
	private void noQuestStarted(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Hello young man"}, true) {
			public void finished() {
				World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Howdy gramps"}) {
					public void finished() {
					owner.setBusy(false);
					npc.unblock();
					}
				});	
			}
		});
	}
	
	private void finished(final Npc npc, final Player owner) {
		World.getDelayedEventHandler().add(new DelayedQuestChat(npc, owner, new String[] {"Congratulations on winning the competition young man"}, true) {
			public void finished() {
				World.getDelayedEventHandler().add(new DelayedQuestChat(owner, npc, new String[] {"Thanks gramps"}) {
					public void finished() {
					owner.setBusy(false);
					npc.unblock();
					}
				});	
			}
		});
	}
	
}