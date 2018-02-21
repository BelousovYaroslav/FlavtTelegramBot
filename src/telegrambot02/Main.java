/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrambot02;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;


public class Main {
    static TelegramBot bot;
    static boolean m_bContinue;
    Long m_lLastChatId;
    static MyUpdateListener m_listener;
    
    
    
    public static void main(String[] args) {
        //create a new Telegram bot object to start talking with Telegram
        bot = new TelegramBot("540532558:AAFYSXpSEhp-QqokFocfcWAWllnGwQmdwvg");
        m_bContinue = true;
        m_listener = new MyUpdateListener( bot);
        bot.setUpdatesListener( m_listener);
        
        new Timer( 5000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if( m_listener.m_lLastChatId != null) {
                    SendMessage request = new SendMessage( m_listener.m_lLastChatId, "TIMER! ;)")
                    .parseMode(ParseMode.HTML)
                    .disableWebPagePreview(true)
                    .disableNotification(true);
                    
                    SendResponse sendResponse = bot.execute(request);
                    boolean ok = sendResponse.isOk();
                    System.out.println( "TIMER send: " + ok);
                }
            }
        }).start();
        
        while( m_bContinue);
    }
}
