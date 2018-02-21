/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrambot02;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import java.util.List;
import static telegrambot02.Main.m_bContinue;

/**
 *
 * @author yaroslav
 */
class MyUpdateListener implements UpdatesListener {
    Long m_lLastChatId;
    TelegramBot m_bot;
    public MyUpdateListener( TelegramBot bot) {
        super();
        m_lLastChatId = null;
        m_bot = bot;
    }
    
    @Override
    public int process(List<Update> updates) {
        System.out.println( "Updates size:" + updates.size());
        if( updates.size() == 10)
            m_bContinue = false;

        // process updates
        Update upd = updates.get( 0);
        System.out.println( "Update(0) text:" + upd.message().text());

        m_lLastChatId = upd.message().chat().id();
        SendMessage request = new SendMessage( m_lLastChatId, "text" + updates.size())
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true)
                .replyToMessageId( upd.message().messageId());
                //.replyMarkup( null);

        // sync
        SendResponse sendResponse = m_bot.execute(request);
        boolean ok = sendResponse.isOk();

        //Message message = sendResponse.message();

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
